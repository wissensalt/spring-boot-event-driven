package com.wissensalt.rnd.sbed.sd.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@Getter
@Setter
public class RequestOrderDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -2946250280503398911L;
    private Double totalPrice;
    private List<RequestOrderDetailDTO> orderDetails;
}
