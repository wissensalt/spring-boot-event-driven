package com.wissensalt.rnd.sbed.oa.endpoint.impl;

import com.wissensalt.rnd.sbed.oa.endpoint.IOrderEndPoint;
import com.wissensalt.rnd.sbed.oa.service.IOrderService;
import com.wissensalt.rnd.sbed.sd.APIErrorBuilder;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.EndPointException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderEndPointImpl implements IOrderEndPoint {

    private final IOrderService orderService;

    @Override
    public ResponseEntity startOrder(HttpServletRequest p_HttpServletRequest, RequestTransactionDTO p_Request) throws EndPointException {
        try {
            return orderService.startOrder(p_HttpServletRequest, p_Request);
        } catch (ServiceException e) {
            return buildErrorException(p_Request.getTransactionCode(), p_HttpServletRequest, e);
        }
    }

    @Override
    public ResponseEntity finishOrder(HttpServletRequest p_HttpServletRequest, String p_TransactionCode) throws EndPointException {
        try {
            orderService.finishOrder(p_TransactionCode);
            return ResponseEntity.ok("Ok");
        } catch (ServiceException e) {
            return buildErrorException(p_TransactionCode, p_HttpServletRequest, e);
        }
    }

    private ResponseEntity buildErrorException(String p_TransactionCode, HttpServletRequest p_HttpServletRequest, Exception e) {
        log.error("Error Finish Order {}", e.toString());
        /*RequestRollBackDTO requestRollBack = new RequestRollBackDTO(p_TransactionCode, "ORDER-API");
        rollBackProducer.sendRollBackInformation(requestRollBack);*/
        return new ResponseEntity<>(APIErrorBuilder.internalServerError(OrderEndPointImpl.class, "Error Conduct Order", p_HttpServletRequest.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
