package com.wissensalt.rnd.sbed.pa.subscriber;

import com.wissensalt.rnd.sbed.pa.service.IProductService;
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

import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.PRODUCT_API;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@MessageEndpoint
@Component
@Slf4j
public class OrderCreatedSubscriber extends ATransactionSubscriber<RequestTransactionDTO> {

    private final IProductService productService;
    private final RollBackProducer rollBackProducer;

    @Override
    public void conductTransaction(Message<RequestTransactionDTO> message) throws SubscriberException {
        log.info("Received Transaction {} ", message.getPayload().toString());
        RequestRollBackDTO requestRollBack = new RequestRollBackDTO(message.getPayload().getTransactionCode(), PRODUCT_API);
        try {
            if (productService.isValidProducts(message.getPayload())) {
                log.info("All Products are valid");
            } else {
                log.warn("One or more products are not valid");
                rollBackProducer.produceMessage(requestRollBack);
            }
        } catch (Exception e) {
            log.error("An Error Occurred when checking Product validity");
            try {
                rollBackProducer.produceMessage(requestRollBack);
            } catch (ProducerException ex) {
                log.error("Failed to send rollback message to kafka {}",e.toString());
            }
        }
    }
}
