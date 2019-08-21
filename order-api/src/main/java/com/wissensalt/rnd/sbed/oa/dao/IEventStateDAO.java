package com.wissensalt.rnd.sbed.oa.dao;

import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.model.EventState;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-14
 **/
public interface IEventStateDAO extends JpaRepository<EventState, Long> {
    EventState findByTransactionCode(String p_TransactionCode) throws DAOException;
}
