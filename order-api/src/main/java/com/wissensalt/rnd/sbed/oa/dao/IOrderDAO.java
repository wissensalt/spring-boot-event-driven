package com.wissensalt.rnd.sbed.oa.dao;

import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
public interface IOrderDAO extends JpaRepository<Order, Long> {

    Order findByTransactionCode(String p_TransactionCode) throws DAOException;
    void deleteByTransactionCode(String p_TransactionCode) throws DAOException;
}
