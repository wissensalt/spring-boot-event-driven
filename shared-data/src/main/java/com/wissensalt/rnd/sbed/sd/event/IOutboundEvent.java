package com.wissensalt.rnd.sbed.sd.event;

import org.springframework.messaging.MessageChannel;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
public interface IOutboundEvent {
    MessageChannel outboundMessage();
}
