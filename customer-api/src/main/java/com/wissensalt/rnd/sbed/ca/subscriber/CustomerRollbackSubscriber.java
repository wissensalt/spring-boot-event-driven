package com.wissensalt.rnd.sbed.ca.subscriber;

import com.wissensalt.rnd.sbed.ca.service.ICustomerService;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.util.messaging.ARollbackSubscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@Slf4j
public class CustomerRollbackSubscriber extends ARollbackSubscriber {

    private final ICustomerService customerService;

    @Override
    public void handleRollBack(RequestRollBackDTO p_Request) throws SubscriberException {
        log.info("Received Rollback Message With Transaction Code {} ", p_Request.getTransactionCode());
        try {
            customerService.handleRollBack(p_Request.getTransactionCode());
        } catch (ServiceException e) {
            log.error("Error Handling Rollback {}", e.toString());
        }
    }
}
