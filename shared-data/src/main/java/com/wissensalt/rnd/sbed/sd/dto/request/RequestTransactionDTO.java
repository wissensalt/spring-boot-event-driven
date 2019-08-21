package com.wissensalt.rnd.sbed.sd.dto.request;

import lombok.*;

import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestTransactionDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -6987138136206410653L;

    private String transactionCode;
    private RequestCustomerDTO customer;
    private RequestOrderDTO order;
    private RequestPaymentDTO payment;
}
