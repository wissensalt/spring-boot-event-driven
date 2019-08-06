package com.wissensalt.rnd.sbed.ca.service;

import com.wissensalt.rnd.sbed.sd.exception.ServiceException;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface ICustomerService {
    void handleRollBack(String p_TransactionId) throws ServiceException;

    void handleCustomer(String p_TransactionCode, String p_CustomerName) throws ServiceException;
}
