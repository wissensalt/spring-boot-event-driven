package com.wissensalt.rnd.sbed.oa.service;

import com.wissensalt.rnd.sbed.oa.dao.IOrderDetailDAO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.model.OrderDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final IOrderDetailDAO orderDetailDAO;

    @Transactional
    @Override
    public void saveDetails(List<OrderDetail> orderDetails) throws ServiceException {
        orderDetailDAO.saveAll(orderDetails);
    }

    @Override
    public void handleRollBack(Long p_OrderId) throws ServiceException {
        try {
            orderDetailDAO.deleteAllByOrOrder_Id(p_OrderId);
        } catch (DAOException e) {
            log.error("Error Delete All Order Details {}", e.toString());
        }
    }
}
