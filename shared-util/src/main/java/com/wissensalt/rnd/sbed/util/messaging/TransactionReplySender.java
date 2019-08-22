package com.wissensalt.rnd.sbed.util.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestReplyTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import com.wissensalt.rnd.sbed.util.producerreplyevent.OrderCreatedReplyProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Component
public class TransactionReplySender {

    private final ObjectMapper objectMapper;
    private final OrderCreatedReplyProducer replyProducer;

    public void send(RequestTransactionDTO p_Request, String p_ServiceName, boolean p_Status) {
        RequestReplyTransactionDTO requestUpdateEvent = new RequestReplyTransactionDTO();
        try {
            requestUpdateEvent.setPayload(objectMapper.writeValueAsString(p_Request));
        } catch (JsonProcessingException e) {
            log.error("Error convert request to json string {}", e.toString());
        }
        requestUpdateEvent.setStatus(p_Status);
        requestUpdateEvent.setServiceName(p_ServiceName);
        requestUpdateEvent.setTransactionCode(p_Request.getTransactionCode());
        try {
            replyProducer.produceMessage(requestUpdateEvent);
        } catch (ProducerException e) {
            log.error("Failed to send reply order created {}", e.toString());
        }
    }
}
