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
@Table(name = "trx_order")
public class Order extends BaseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 2290871704937621007L;

    @Column(name = "trx_code", unique = true)
    private String transactionCode;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderDetail> orderDetails;
}
