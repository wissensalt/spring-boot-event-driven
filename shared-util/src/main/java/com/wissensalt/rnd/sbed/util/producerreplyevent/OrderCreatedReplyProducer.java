package com.wissensalt.rnd.sbed.util.producerreplyevent;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestReplyTransactionDTO;
import com.wissensalt.rnd.sbed.sd.event.output.IEventOrderCreatedReplyOutput;
import com.wissensalt.rnd.sbed.util.messaging.AProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-04
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class OrderCreatedReplyProducer extends AProducer<RequestReplyTransactionDTO> {

    private final IEventOrderCreatedReplyOutput output;

    @Override
    public MessageChannel getOutboundMessageChannel() {
        return output.outboundMessage();
    }
}
