package com.wissensalt.rnd.sbed.ia.dao;

import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IInventoryDAO extends JpaRepository<Inventory, Long> {

    List<Inventory> findByTransactionCode(String p_TransactionCode) throws DAOException;
}
