package com.wissensalt.rnd.sbed.payment.subscriber;

import com.wissensalt.rnd.sbed.payment.service.IPaymentService;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName;
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

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@MessageEndpoint
@Component
@Slf4j
public class OrderCreatedSubscriber extends ATransactionSubscriber<RequestTransactionDTO> {

    private final IPaymentService paymentService;
    private final RollBackProducer rollBackProducer;

    @Override
    public void conductTransaction(Message<RequestTransactionDTO> message) throws SubscriberException {
        log.info("Received Transaction {} ", message.getPayload().toString());
        try {
            paymentService.conductPayment(message.getPayload());
        } catch (ServiceException e) {
            log.error("Error handling Payment {}", e.toString());
            try {
                rollBackProducer.produceMessage(new RequestRollBackDTO(message.getPayload().getTransactionCode(), ServiceName.PAYMENT_API));
            } catch (ProducerException ex) {
                log.error("Error send rollback to kafka {}", e.toString());
            }
        }
    }
}
