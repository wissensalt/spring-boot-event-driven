package com.wissensalt.rnd.sbed.sd.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-21
 **/
@Getter
@Setter
public class ContentAPIRequestData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 637935412738517898L;
    private String remoteAddress;
    private int remotePort;
    private String remoteUser;
    private String method;
    private String requestUrl;
    private List<String> headers;
    private List<String> cookies;
    private String payload;
}
