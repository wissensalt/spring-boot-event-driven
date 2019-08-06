package com.wissensalt.rnd.sbed.sd.dto.response;

import lombok.*;

import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCustomerDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 4837715044057138955L;
    private String transactionCode;
    private Long id;
}
