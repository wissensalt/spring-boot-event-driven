package com.wissensalt.rnd.sbed.ia.service;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IInventoryService {

    void conductTransaction(RequestTransactionDTO p_Request) throws ServiceException;

    void handleRollBack(RequestRollBackUpdateCartDTO p_Request) throws ServiceException;
}
