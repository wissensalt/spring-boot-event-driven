package com.wissensalt.rnd.sbed.pa.dao;

import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-04
 **/
public interface IProductDAO extends JpaRepository<Product, Long> {

    @Query("SELECT count(p.id) FROM Product p WHERE p.id IN (?1)")
    Integer countByProductIds(List<Long> p_ProductIds) throws DAOException;

    @Query("SELECT p FROM Product p WHERE p.id IN (?1)")
    List<Product> findByProductIds(List<Long> p_ProductIds) throws DAOException;

    List<Product> findByTransactionCode(String p_TranscationCode) throws DAOException;
}
