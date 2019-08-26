package com.wissensalt.rnd.sbed.ca.producer;

import com.wissensalt.rnd.sbed.sd.dto.response.ResponseCustomerDTO;
import com.wissensalt.rnd.sbed.sd.event.output.IEventCustomerInfoOutput;
import com.wissensalt.rnd.sbed.util.messaging.AProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerProducer extends AProducer<ResponseCustomerDTO> {

    private final IEventCustomerInfoOutput eventCustomerInfoOutput;

    @Override
    public MessageChannel getOutboundMessageChannel() {
        return eventCustomerInfoOutput.outboundMessage();
    }
}
