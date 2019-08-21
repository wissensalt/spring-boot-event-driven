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
@Table(name = "event_state_detail")
public class EventStateDetail extends BaseData {
    /**
     *
     *
     */
    private static final long serialVersionUID = 3810245500967566627L;

    @Column(name = "trx_code")
    private String transactionCode;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "payload")
    private String payload;
}
