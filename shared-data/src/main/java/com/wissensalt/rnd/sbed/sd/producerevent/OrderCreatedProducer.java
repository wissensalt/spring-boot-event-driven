package com.wissensalt.rnd.sbed.sd.producerevent;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestUpdateEventStateDetailDTO;
import com.wissensalt.rnd.sbed.sd.event.output.IEventOrderCreatedOutput;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-04
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class OrderCreatedProducer {

    private final IEventOrderCreatedOutput eventOrder;

    public void sendUpdateCart(final RequestUpdateEventStateDetailDTO request) throws ProducerException {
        log.info("Send Update Cart {}", request.toString());

        MessageChannel messageChannel = eventOrder.outboundUpdateCart();
        messageChannel.send(MessageBuilder
                .withPayload(request)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
