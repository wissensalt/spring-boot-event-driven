package com.wissensalt.rnd.sbed.sd.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@Getter
@Setter
@Entity
@Table(name = "trx_inventory_detail")
public class InventoryDetail extends BaseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 1889728297031524017L;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "stock_in")
    private Integer stockIn;

    @Column(name = "stock_out")
    private Integer stockOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_header_id")
    private Inventory inventory;
}
