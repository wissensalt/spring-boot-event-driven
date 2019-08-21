package com.wissensalt.rnd.sbed.sd;

import lombok.*;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Greeting {

    private Long timeStamp;
    private String message;
}
