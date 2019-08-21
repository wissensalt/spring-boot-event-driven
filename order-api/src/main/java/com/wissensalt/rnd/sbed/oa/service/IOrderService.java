package com.wissensalt.rnd.sbed.oa.service;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.response.ResponseCustomerDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
public interface IOrderService {

    ResponseEntity startOrder(HttpServletRequest p_HttpServletRequest, RequestTransactionDTO p_Request) throws ServiceException;

    void conductRollBackOrder(RequestRollBackDTO p_Request) throws ServiceException;

    void updateOrder(ResponseCustomerDTO p_Customer) throws ServiceException;

    void finishOrder(String p_TransactionCode) throws ServiceException;
}
