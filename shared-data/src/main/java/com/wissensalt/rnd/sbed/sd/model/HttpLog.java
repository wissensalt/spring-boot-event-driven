package com.wissensalt.rnd.sbed.sd.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-21
 **/
@Getter
@Setter
@Entity
@Table(name = "http_log")
public class HttpLog extends BaseData {
    /**
     *
     *
     */
    private static final long serialVersionUID = -1357516636251044949L;

    @Column(name = "request_time")
    private Date requestTime;

    @Column(name = "endpoint_name")
    private String endPointName;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;
}
