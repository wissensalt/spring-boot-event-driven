package com.wissensalt.rnd.sbed.oa.service;

import com.wissensalt.rnd.sbed.oa.dao.IOrderDAO;
import com.wissensalt.rnd.sbed.oa.producer.EventOrderProducer;
import com.wissensalt.rnd.sbed.oa.validation.OrderValidator;
import com.wissensalt.rnd.sbed.sd.APIErrorBuilder;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestOrderDetailDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.response.ResponseCustomerDTO;
import com.wissensalt.rnd.sbed.sd.dto.response.ResponseData;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.mapper.OrderDetailMapper;
import com.wissensalt.rnd.sbed.sd.mapper.OrderMapper;
import com.wissensalt.rnd.sbed.sd.model.Order;
import com.wissensalt.rnd.sbed.sd.model.OrderDetail;
import com.wissensalt.rnd.sbed.sd.producer.RollBackProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final EventOrderProducer eventOrderProducer;
    private final OrderValidator orderValidator;
    private final RollBackProducer rollBackProducer;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public ResponseEntity conductOrder(HttpServletRequest p_HttpServletRequest, RequestTransactionDTO p_Request) throws ServiceException {
        ResponseEntity result = ResponseEntity.ok(new ResponseData("200", "Success Conduct Order"));
        log.info("start conduct order");
        RequestRollBackUpdateCartDTO requestRollBack = new RequestRollBackUpdateCartDTO(p_Request.getTransactionCode(), ServiceName.ORDER_API);
        if (orderValidator.validate(p_Request)) {
            Order order = orderDAO.save(orderMapper.toOrderModel(p_Request));
            log.info("Success Saving Order");

            try {
                saveDetails(order, p_Request, requestRollBack);
            } catch (ServiceException e) {
                rollBackProducer.sendRollBackInformation(requestRollBack);
                result = new ResponseEntity<>(APIErrorBuilder.internalServerError(OrderServiceImpl.class, "Transaction Rolled Back", p_HttpServletRequest.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            rollBackProducer.sendRollBackInformation(requestRollBack);
            result = new ResponseEntity<>(APIErrorBuilder.internalServerError(OrderServiceImpl.class, "Transaction Rolled Back", p_HttpServletRequest.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ServiceException("Order Is Not valid, transaction rolled back");
        }

        try {
            eventOrderProducer.sendUpdateCart(p_Request);
        } catch (ProducerException e) {
            log.error("Error Send Update Cart Message to Kafka Broker {}", e.toString());
            rollBackProducer.sendRollBackInformation(requestRollBack);
        }
        return result;
    }


    private void saveDetails(Order order, RequestTransactionDTO p_Request, RequestRollBackUpdateCartDTO p_RequestRollBack) throws ServiceException {
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
            rollBackProducer.sendRollBackInformation(p_RequestRollBack);
            throw new ServiceException("Failed to Insert Order Details, Transaction Rolled Back");
        }
    }

    @Transactional
    @Override
    public void conductRollBackOrder(RequestRollBackUpdateCartDTO p_Request) throws ServiceException {
        try {
            if (p_Request.getRollbackSource().equals(ServiceName.ORDER_API)) {
                log.warn("Nothing To Do Rollback has been handled");
            } else {
                Order order = orderDAO.findByTransactionCode(p_Request.getTransactionCode());
                if (!Objects.isNull(order)) {
                    orderDetailService.handleRollBack(order.getId());
                    orderDAO.deleteByTransactionCode(p_Request.getTransactionCode());
                    log.info("Success handle Rollback");
                }
            }
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
}
