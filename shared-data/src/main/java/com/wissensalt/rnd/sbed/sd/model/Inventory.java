package com.wissensalt.rnd.sbed.sd.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@Getter
@Setter
@Entity
@Table(name = "trx_inventory_header")
public class Inventory extends BaseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 1539319126944981241L;

    @Column(name = "trx_code", unique = true)
    private String transactionCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inventory")
    private Set<InventoryDetail> inventoryDetails;
}
