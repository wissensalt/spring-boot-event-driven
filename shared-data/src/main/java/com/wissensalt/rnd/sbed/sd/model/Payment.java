package com.wissensalt.rnd.sbed.sd.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-06
 **/
@Getter
@Setter
@Table(name = "trx_payment")
@Entity
public class Payment extends BaseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 1695529876567721816L;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "payment_total")
    private Double paymentTotal;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "payment_status")
    private String paymentStatus;
}
