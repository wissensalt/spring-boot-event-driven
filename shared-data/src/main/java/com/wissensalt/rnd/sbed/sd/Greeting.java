package com.wissensalt.rnd.sbed.sd;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
@Getter
@Setter
@ToString
@Builder
public class Greeting {

    private Long timeStamp;
    private String message;
}
