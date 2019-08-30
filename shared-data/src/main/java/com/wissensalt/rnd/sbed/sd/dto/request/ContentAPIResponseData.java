package com.wissensalt.rnd.sbed.sd.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
@Getter
@Setter
public class ContentAPIResponseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -7992314610409878305L;
    private String host;
    private String body;
}
