package com.wissensalt.rnd.sbed.ca.service;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface ICustomerService {
    void handleRollBack(String p_TransactionId) throws ServiceException;

    void handleCustomer(RequestTransactionDTO p_Request) throws ServiceException;
}
