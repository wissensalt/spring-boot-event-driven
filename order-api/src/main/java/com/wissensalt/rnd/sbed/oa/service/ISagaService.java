package com.wissensalt.rnd.sbed.oa.service;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestReplyTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-14
 **/
public interface ISagaService {

    void broadcastOrderTransaction(RequestTransactionDTO p_Request) throws ServiceException;

    void updateEventStateDetail(RequestReplyTransactionDTO p_Request) throws ServiceException;

    void saveEventStateHeader(RequestTransactionDTO p_Request) throws ServiceException;

    void handleRollback(RequestRollBackDTO p_Request) throws ServiceException;
}
