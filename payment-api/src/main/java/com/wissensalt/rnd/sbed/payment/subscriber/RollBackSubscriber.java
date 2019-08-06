package com.wissensalt.rnd.sbed.payment.subscriber;

import com.wissensalt.rnd.sbed.payment.service.IPaymentService;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
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
public class RollBackSubscriber {

    private final IPaymentService paymentService;

    @StreamListener(AppConstant.EventRollBack.INPUT_ROLLBACK)
    public void handleRollBack(@Payload RequestRollBackUpdateCartDTO p_Request) throws SubscriberException {
        log.info("Received Rollback Message With Transaction Code {} ", p_Request.getTransactionCode());
        try {
            paymentService.handleRollBack(p_Request);
        } catch (ServiceException e) {
            log.error("Error Handling Rollback {}", e.toString());
        }
    }
}
