package com.wissensalt.rnd.sbed.oa.service;

import com.wissensalt.rnd.sbed.sd.Greeting;
import com.wissensalt.rnd.sbed.sd.event.output.GreetingStreamOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class GreetingService {

    private final GreetingStreamOutput greetingStreamOutput;

    public void sendGreeting(final Greeting greeting) {
        log.info("Send Greeting {}", greeting.toString());
        MessageChannel messageChannel = greetingStreamOutput.outboundGreetings();
        messageChannel.send(MessageBuilder
                .withPayload(greeting)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
