package com.wissensalt.rnd.sbed.ca.dao;

import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
public interface ICustomerDAO extends JpaRepository<Customer, Long> {

    Customer findByUserName(String p_UserName) throws DAOException;
}
