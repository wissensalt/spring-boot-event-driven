package com.wissensalt.rnd.sbed.pa.subscriber;

import com.wissensalt.rnd.sbed.pa.service.IProductService;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.util.messaging.ARollbackSubscriber;
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
@Component
@Slf4j
public class RollBackSubscriber extends ARollbackSubscriber {

    private final IProductService productService;

    @Override
    public void handleRollBack(RequestRollBackDTO p_Request) throws SubscriberException {
        log.info("Received Rollback Message With Transaction Code {} ", p_Request);
        try {
            productService.handleRollback(p_Request);
        } catch (ServiceException e) {
            log.error("Error Conduct Rollback Order {}", e.toString());
        }
    }
}
