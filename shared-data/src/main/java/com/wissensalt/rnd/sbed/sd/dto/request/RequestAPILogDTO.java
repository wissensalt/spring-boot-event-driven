package com.wissensalt.rnd.sbed.sd.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-21
 **/
@Getter
@Setter
public class RequestAPILogDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -2029528692465582941L;

    private Date timeStamp;
    private String endPointName;
    private ContentAPIRequestData requestData;
    private ContentAPIResponseData responseData;
}
