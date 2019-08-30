package com.wissensalt.rnd.sbed.oa.producer;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.event.output.IEventOrderCreatedOutput;
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
public class EventOrderProducer extends AProducer<RequestTransactionDTO> {

    private final IEventOrderCreatedOutput eventOrder;

    @Override
    public MessageChannel getOutboundMessageChannel() {
        return eventOrder.outboundMessage();
    }
}
