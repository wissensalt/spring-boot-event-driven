package com.wissensalt.rnd.sbed.sd.dto.request;

import lombok.*;

import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-06
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestRollBackUpdateCartDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 4308112262874986800L;
    private String transactionCode;
    private String rollbackSource;
}
