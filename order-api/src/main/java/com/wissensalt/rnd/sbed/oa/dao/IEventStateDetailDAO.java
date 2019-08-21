package com.wissensalt.rnd.sbed.oa.dao;

import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.model.EventStateDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-14
 **/
public interface IEventStateDetailDAO extends JpaRepository<EventStateDetail, Long> {
    List<EventStateDetail> findByTransactionCode(String p_TransactionCode) throws DAOException;

    List<EventStateDetail> findByTransactionCodeAndStatus(String p_TransactionCode, boolean p_Status) throws DAOException;

    EventStateDetail findByTransactionCodeAndServiceName(String p_TransactionCode, String p_ServiceName) throws DAOException;
}
