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
public class RequestOrderDetailDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -311518789739012209L;
    private Long productId;
    private Integer quantity;
    private Double price;
}
