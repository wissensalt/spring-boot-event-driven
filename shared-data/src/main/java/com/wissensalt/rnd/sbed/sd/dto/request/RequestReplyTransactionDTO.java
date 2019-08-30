package com.wissensalt.rnd.sbed.sd.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-14
 **/
@ToString
@Getter
@Setter
public class RequestReplyTransactionDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 4706700051233177187L;
    private String transactionCode;
    private String serviceName;
    private String payload;
    private Boolean status;
    private String remarks;
}
