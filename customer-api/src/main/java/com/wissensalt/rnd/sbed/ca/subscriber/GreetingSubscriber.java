package com.wissensalt.rnd.sbed.ca.subscriber;

import com.wissensalt.rnd.sbed.sd.Greeting;
import com.wissensalt.rnd.sbed.sd.event.input.GreetingStreamInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
@MessageEndpoint
@Component
@Slf4j
public class GreetingSubscriber {

    @StreamListener(GreetingStreamInput.INPUT)
    public void handleGreeting(@Payload Greeting greeting) {
        log.info("Received Greeting {} ", greeting);
    }
}
