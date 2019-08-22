package com.wissensalt.rnd.sbed.sd.event.output;

import com.wissensalt.rnd.sbed.sd.event.IOutboundEvent;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
public interface GreetingStreamOutput extends IOutboundEvent {
    String OUTPUT = "greetings-out";

    @Output(OUTPUT)
    @Override
    MessageChannel outboundMessage();
}
