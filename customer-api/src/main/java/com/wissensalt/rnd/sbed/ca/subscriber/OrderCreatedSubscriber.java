package com.wissensalt.rnd.sbed.ca.subscriber;

import com.wissensalt.rnd.sbed.ca.service.ICustomerService;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.util.messaging.ATransactionSubscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@MessageEndpoint
@Component
@Slf4j
public class OrderCreatedSubscriber extends ATransactionSubscriber<RequestTransactionDTO> {

    private final ICustomerService customerService;

    @Override
    public void conductTransaction(Message<RequestTransactionDTO> message) throws SubscriberException {
        log.info("Received Transaction {} ", message.getPayload());
        try {
            customerService.handleCustomer(message.getPayload());
            Acknowledgment ack = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
            if (!Objects.isNull(ack)) {
                log.info("Acknowledgement Provided");
                ack.acknowledge();
            }
        } catch (ServiceException e) {
            log.error("Error handling customer {}", e.toString());
        }
    }
}
