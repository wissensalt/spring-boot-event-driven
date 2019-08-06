package com.wissensalt.rnd.sbed.sd.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@Getter
@Setter
public class RequestPaymentDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 5568712338010151147L;

    private Double paymentTotal;
    private String paymentType;
    private String paymentStatus;
}
