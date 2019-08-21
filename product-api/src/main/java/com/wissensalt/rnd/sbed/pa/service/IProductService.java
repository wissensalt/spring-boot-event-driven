package com.wissensalt.rnd.sbed.pa.service;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IProductService {

    Boolean isValidProducts(RequestTransactionDTO p_Request) throws ServiceException;

    void handleRollback(RequestRollBackDTO p_RequestRollBack) throws ServiceException;
}
