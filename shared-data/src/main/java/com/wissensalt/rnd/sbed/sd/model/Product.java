package com.wissensalt.rnd.sbed.sd.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-04
 **/
@Getter
@Setter
@Table(name = "mst_product")
@Entity
public class Product extends BaseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 1926038225442622623L;

    @Column(name = "name")
    private String name;

    @Column(name = "current_stock")
    private Integer currentStock;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "previous_stock")
    private Integer previousStock;
}
