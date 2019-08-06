package com.wissensalt.rnd.sbed.oa.dao;

import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IOrderDetailDAO extends JpaRepository<OrderDetail, Long> {

    void deleteAllByOrOrder_Id(Long p_OrderId) throws DAOException;
}
