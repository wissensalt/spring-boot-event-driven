package com.wissensalt.rnd.sbed.oa.service;

import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.model.OrderDetail;

import java.util.List;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IOrderDetailService  {

    void saveDetails(List<OrderDetail> orderDetails) throws ServiceException;

    void handleRollBack(Long p_OrderId) throws ServiceException;
}
