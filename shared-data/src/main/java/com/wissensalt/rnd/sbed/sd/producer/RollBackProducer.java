package com.wissensalt.rnd.sbed.sd.producer;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.event.output.IEventRollBackOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class RollBackProducer {
    private final IEventRollBackOutput eventRollBackOutput;

    public void sendRollBackInformation(final RequestRollBackUpdateCartDTO request) {
        log.info("Broadcast RollBack Information With Trx Code : {}", request);
        MessageChannel messageChannel = eventRollBackOutput.outBoundRollBackEveryThing();
        messageChannel.send(MessageBuilder
                .withPayload(request)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
