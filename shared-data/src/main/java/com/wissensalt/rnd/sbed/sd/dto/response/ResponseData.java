package com.wissensalt.rnd.sbed.sd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -7064598828207170971L;
    private String responseCode;
    private String responseMessage;
}
