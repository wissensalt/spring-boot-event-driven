package com.wissensalt.rnd.sbed.payment.subscriber;

import com.wissensalt.rnd.sbed.payment.service.IPaymentService;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.util.messaging.ATransactionSubscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
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

    @Override
    public void onMessageArrived(RequestTransactionDTO p_Message) throws SubscriberException {
        log.info("Received Transaction {} ", p_Message.toString());
        try {
            paymentService.conductPayment(p_Message);
        } catch (ServiceException e) {
            log.error("Error handling Payment {}", e.toString());
        }
    }
}
