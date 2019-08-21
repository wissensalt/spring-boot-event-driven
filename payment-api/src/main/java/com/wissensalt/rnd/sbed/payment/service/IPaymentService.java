package com.wissensalt.rnd.sbed.payment.service;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-06
 **/
public interface IPaymentService {

    void conductPayment(RequestTransactionDTO p_Request) throws ServiceException;

    void handleRollBack(RequestRollBackDTO p_Request) throws ServiceException;
}
