package com.wissensalt.rnd.sbed.util.producerrollback;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.event.output.IEventRollBackOutput;
import com.wissensalt.rnd.sbed.util.messaging.AProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     Produce rollback event with payload {@link RequestRollBackDTO}
 * </p>
 *
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class RollBackProducer extends AProducer<RequestRollBackDTO> {

    private final IEventRollBackOutput eventRollBackOutput;

    @Override
    public MessageChannel getOutboundMessageChannel() {
        return eventRollBackOutput.outboundMessage();
    }
}
