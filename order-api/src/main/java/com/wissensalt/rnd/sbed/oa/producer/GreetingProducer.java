package com.wissensalt.rnd.sbed.oa.producer;

import com.wissensalt.rnd.sbed.sd.Greeting;
import com.wissensalt.rnd.sbed.sd.event.output.GreetingStreamOutput;
import com.wissensalt.rnd.sbed.util.messaging.AProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class GreetingProducer extends AProducer<Greeting> {

    private final GreetingStreamOutput greetingStreamOutput;

    @Override
    public MessageChannel getOutboundMessageChannel() {
        return greetingStreamOutput.outboundMessage();
    }
}
