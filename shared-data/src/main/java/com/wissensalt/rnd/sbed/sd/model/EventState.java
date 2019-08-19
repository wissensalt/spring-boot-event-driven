package com.wissensalt.rnd.sbed.sd.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-14
 **/
@Getter
@Setter
@Entity
@Table(name = "event_state")
public class EventState extends BaseData {
    /**
     *
     *
     */
    private static final long serialVersionUID = -6343726199064367771L;

    @Column(name = "trx_code", unique = true)
    private String transactionCode;

    @Column(name = "trx_status")
    private Boolean transactionStatus;
}
