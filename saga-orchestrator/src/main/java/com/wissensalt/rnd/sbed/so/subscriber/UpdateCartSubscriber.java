package com.wissensalt.rnd.sbed.so.subscriber;

import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestUpdateEventStateDetailDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.sd.producer.RollBackProducer;
import com.wissensalt.rnd.sbed.so.service.ISagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.SAGA_ORCHESTRATOR;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@MessageEndpoint
@Component
@Slf4j
public class UpdateCartSubscriber {

//    private final RollBackProducer rollBackProducer;
    private final ISagaService sagaService;

    @StreamListener(AppConstant.EventUpdateCart.INPUT_UPDATE_CART)
    public void updateEventState(@Payload RequestUpdateEventStateDetailDTO p_Request) throws SubscriberException {
        log.info("Received Event State {} ", p_Request.toString());
//        RequestRollBackUpdateCartDTO requestRollBack = new RequestRollBackUpdateCartDTO(p_Request.getTransactionCode(), SAGA_ORCHESTRATOR);

        try {
            sagaService.updateEventStateDetail(p_Request);
        } catch (ServiceException e) {
            log.error("Error update event state {}", e.toString());
//            rollBackProducer.sendRollBackInformation(requestRollBack);
        }
    }
}
