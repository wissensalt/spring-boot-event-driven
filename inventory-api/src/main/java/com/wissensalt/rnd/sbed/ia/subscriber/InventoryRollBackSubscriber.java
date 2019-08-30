package com.wissensalt.rnd.sbed.ia.subscriber;

import com.wissensalt.rnd.sbed.ia.service.IInventoryService;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.util.messaging.ARollbackSubscriber;
import com.wissensalt.rnd.sbed.util.messaging.IRollbackSubscriber;
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
@Component
@Slf4j
public class InventoryRollBackSubscriber extends ARollbackSubscriber {

    private final IInventoryService inventoryService;

    @Override
    public void handleRollBack(RequestRollBackDTO p_Request) throws SubscriberException {
        log.info("Received Rollback Message With Transaction Code {} ", p_Request.getTransactionCode());
        try {
            inventoryService.handleRollBack(p_Request);
        } catch (ServiceException e) {
            log.error("Error Handling Rollback {}", e.toString());
        }
    }
}
