package com.wissensalt.rnd.sbed.ia.dao;

import com.wissensalt.rnd.sbed.sd.model.InventoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IInventoryDetailDAO extends JpaRepository<InventoryDetail, Long> {

    List<InventoryDetail> findByInventory_TransactionCode(String p_TransactionCode);
}
