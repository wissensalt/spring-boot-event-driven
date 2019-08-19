package com.wissensalt.rnd.sbed.ca.producer;

import com.wissensalt.rnd.sbed.sd.dto.response.ResponseCustomerDTO;
import com.wissensalt.rnd.sbed.sd.event.output.IEventCustomerInfoOutput;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
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
public class CustomerProducer {

    private final IEventCustomerInfoOutput eventCustomerInfoOutput;

    public void sendCustomerInformation(ResponseCustomerDTO p_Response) throws ProducerException {
        log.info("Customer Has Been Processed : {}", p_Response);
        MessageChannel messageChannel = eventCustomerInfoOutput.outBoundCustomerInfo();
        messageChannel.send(MessageBuilder
                .withPayload(p_Response)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
