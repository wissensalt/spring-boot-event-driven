package com.wissensalt.rnd.sbed.payment.dao;

import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-06
 **/
public interface IPaymentDAO extends JpaRepository<Payment, Long> {

    Payment findByTransactionCode(String p_TransactionCode) throws DAOException;
}
