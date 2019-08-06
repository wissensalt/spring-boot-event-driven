package com.wissensalt.rnd.sbed.pa.service;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestOrderDetailDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;

import java.util.List;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IProductService {

    Boolean isValidProducts(String p_TransactionCode, List<RequestOrderDetailDTO> p_OrderDetails) throws ServiceException;

    void handleRollback(RequestRollBackUpdateCartDTO p_RequestRollBack) throws ServiceException;
}
