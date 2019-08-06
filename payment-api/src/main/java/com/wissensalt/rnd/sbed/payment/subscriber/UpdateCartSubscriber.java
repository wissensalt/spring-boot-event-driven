package com.wissensalt.rnd.sbed.payment.subscriber;

import com.wissensalt.rnd.sbed.payment.service.IPaymentService;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.sd.producer.RollBackProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
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
public class UpdateCartSubscriber {

    private final IPaymentService paymentService;
    private final RollBackProducer rollBackProducer;

    @StreamListener(AppConstant.EventUpdateCart.INPUT_UPDATE_CART)
    public void handleCustomer(@Payload RequestTransactionDTO p_Request) throws SubscriberException {
        log.info("Received Transaction {} ", p_Request.toString());
        try {
            paymentService.conductPayment(p_Request);
        } catch (ServiceException e) {
            log.error("Error handling Payment {}", e.toString());
            rollBackProducer.sendRollBackInformation(new RequestRollBackUpdateCartDTO(p_Request.getTransactionCode(), ServiceName.PAYMENT_API));
        }
    }
}
