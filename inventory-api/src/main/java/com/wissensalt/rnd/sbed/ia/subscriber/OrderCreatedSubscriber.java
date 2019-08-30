package com.wissensalt.rnd.sbed.ia.subscriber;

import com.wissensalt.rnd.sbed.ia.service.IInventoryService;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.util.messaging.ATransactionSubscriber;
import com.wissensalt.rnd.sbed.util.producerrollback.RollBackProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.INVENTORY_API;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@MessageEndpoint
@Component
@Slf4j
public class OrderCreatedSubscriber extends ATransactionSubscriber<RequestTransactionDTO> {

    private final IInventoryService inventoryService;
    private final RollBackProducer rollBackProducer;

    @Override
    public void onMessageArrived(Message<RequestTransactionDTO> p_Message) throws SubscriberException {
        log.info("Received Transaction {} ", p_Message.getPayload().toString());
        RequestRollBackDTO requestRollBack = new RequestRollBackDTO(p_Message.getPayload().getTransactionCode(), INVENTORY_API);
        try {
            inventoryService.conductTransaction(p_Message.getPayload());
        } catch (ServiceException e) {
            log.error("Error Conduct Transaction Inventory {}", e.toString());
            try {
                rollBackProducer.produceMessage(requestRollBack);
            } catch (ProducerException ex) {
                log.error("Failed to send rollback message {}", e.toString());
            }
        }
    }
}
