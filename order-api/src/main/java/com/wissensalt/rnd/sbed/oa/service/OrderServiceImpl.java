package com.wissensalt.rnd.sbed.oa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissensalt.rnd.sbed.oa.dao.IOrderDAO;
import com.wissensalt.rnd.sbed.oa.validation.OrderValidator;
import com.wissensalt.rnd.sbed.sd.APIErrorBuilder;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestOrderDetailDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestReplyTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.response.ResponseCustomerDTO;
import com.wissensalt.rnd.sbed.sd.dto.response.ResponseData;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.mapper.OrderDetailMapper;
import com.wissensalt.rnd.sbed.sd.mapper.OrderMapper;
import com.wissensalt.rnd.sbed.sd.model.Order;
import com.wissensalt.rnd.sbed.sd.model.OrderDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderDAO orderDAO;
    private final IOrderDetailService orderDetailService;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderValidator orderValidator;
    private final ISagaService sagaService;
    private final ObjectMapper objectMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public ResponseEntity startOrder(HttpServletRequest p_HttpServletRequest, RequestTransactionDTO p_Request) throws ServiceException {
        ResponseEntity result = ResponseEntity.ok(new ResponseData("200", "Success Conduct Order"));
        log.info("start conduct order");
        RequestRollBackDTO requestRollBack = new RequestRollBackDTO(p_Request.getTransactionCode(), ServiceName.ORDER_API);
        if (orderValidator.validate(p_Request)) {
            Order order = orderMapper.toOrderModel(p_Request);
            order.setStatus(false);

            order = orderDAO.save(order);
            log.info("Success Saving Order");

            try {
                saveDetails(order, p_Request, requestRollBack);
                sagaService.saveEventStateHeader(p_Request);
                sagaService.broadcastOrderTransaction(p_Request);
                saveEventStateDetail(p_Request);
            } catch (ServiceException e) {
                result = new ResponseEntity<>(APIErrorBuilder.internalServerError(OrderServiceImpl.class, "Transaction Rolled Back", p_HttpServletRequest.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            result = new ResponseEntity<>(APIErrorBuilder.internalServerError(OrderServiceImpl.class, "Transaction Rolled Back", p_HttpServletRequest.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ServiceException("Order Is Not valid, transaction rolled back");
        }

        return result;
    }

    private void saveEventStateDetail(RequestTransactionDTO p_Request) {
        String eventState = Strings.EMPTY;
        try {
            eventState = objectMapper.writeValueAsString(p_Request);
        } catch (JsonProcessingException e) {
            log.error("Error convert request to string JSON {}", e.toString());
        }
        RequestReplyTransactionDTO requestEventState = new RequestReplyTransactionDTO();
        requestEventState.setTransactionCode(p_Request.getTransactionCode());
        requestEventState.setServiceName(ServiceName.ORDER_API);
        requestEventState.setStatus(true);
        requestEventState.setPayload(eventState);
        try {
            sagaService.updateEventStateDetail(requestEventState);
        } catch (ServiceException e) {
            log.error("Error update event state detail {}", e.toString());
        }
    }

    private void saveDetails(Order order, RequestTransactionDTO p_Request, RequestRollBackDTO p_RequestRollBack) throws ServiceException {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (RequestOrderDetailDTO orderDetail : p_Request.getOrder().getOrderDetails()) {
            OrderDetail detail = orderDetailMapper.toOrderDetailModel(orderDetail);
            detail.setOrder(order);
            orderDetailList.add(detail);
        }

        try {
            orderDetailService.saveDetails(orderDetailList);
            log.info("Success Saving Order Details");
        } catch (ServiceException e) {
            log.error("Failed to Insert Order Details, Transaction Rolled Back");
            throw new ServiceException("Failed to Insert Order Details, Transaction Rolled Back");
        }
    }

    @Transactional
    @Override
    public void conductRollBackOrder(RequestRollBackDTO p_Request) throws ServiceException {
        try {
            Order order = orderDAO.findByTransactionCode(p_Request.getTransactionCode());
            if (!Objects.isNull(order)) {
                orderDetailService.handleRollBack(order.getId());
                orderDAO.deleteByTransactionCode(p_Request.getTransactionCode());
                log.info("Success handle Rollback");
            }

            RequestReplyTransactionDTO requestUpdate = new RequestReplyTransactionDTO();
            requestUpdate.setTransactionCode(p_Request.getTransactionCode());
            requestUpdate.setServiceName(p_Request.getRollbackSource());
            requestUpdate.setStatus(false);
            requestUpdate.setRemarks("Rolled Back");
            sagaService.updateEventStateDetail(requestUpdate);
        } catch (DAOException e) {
            log.error("Error Handle Rollback {}", e.toString());
        }
    }

    @Transactional
    @Override
    public void updateOrder(ResponseCustomerDTO p_Customer) throws ServiceException {
        try {
            Order order = orderDAO.findByTransactionCode(p_Customer.getTransactionCode());
            if (!Objects.isNull(order)) {
                order.setCustomerId(p_Customer.getId());
                orderDAO.save(order);
                log.info("Success Update Order with new Customer Info");
            } else {
                log.error("Order With Transaction Code {} is Not Found", p_Customer.getTransactionCode());
            }
        } catch (DAOException e) {
            log.error("Error Find Order with Transcation Code {}", p_Customer.getTransactionCode());
        }
    }

    @Override
    public void finishOrder(String p_TransactionCode) throws ServiceException {
        try {
            Order order = orderDAO.findByTransactionCode(p_TransactionCode);
            if (!Objects.isNull(order)) {
                order.setStatus(true);
                orderDAO.save(order);
                //TODO send information to client that transaction is finished
            } else {
                log.error("Order with trx code {} is not found", p_TransactionCode);
                throw new ServiceException("Order is not found");
            }
        } catch (DAOException e) {
            log.error("Error Finish Transaction");
        }
    }
}
