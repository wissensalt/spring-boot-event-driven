package com.wissensalt.rnd.sbed.ca.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissensalt.rnd.sbed.ca.service.ICustomerService;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@MessageEndpoint
@Component
@Slf4j
public class UpdateCartSubscriber {

    private final ICustomerService customerService;
    private final ObjectMapper objectMapper;

    @StreamListener(AppConstant.EventUpdateCart.INPUT_UPDATE_CART)
    public void handleCustomer(Message<?> message) throws SubscriberException {
        log.info("Received Transaction {} ", message.getPayload().toString());
        RequestTransactionDTO payload = null;
        try {
            payload = objectMapper.readValue(message.getPayload().toString(), RequestTransactionDTO.class);
        } catch (IOException e) {
            log.error("Error parsing payload from message broker {}", e.toString());
        }
        try {
            customerService.handleCustomer(payload.getTransactionCode(), payload.getCustomer().getName());
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
