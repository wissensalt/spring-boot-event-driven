package com.wissensalt.rnd.sbed.oa.subscriber;

import com.wissensalt.rnd.sbed.oa.service.IOrderService;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.response.ResponseCustomerDTO;
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
public class CustomerInfoSubscriber {
    private final IOrderService orderService;

    @StreamListener(AppConstant.EventCustomerInfo.INPUT_CUSTOMER_INFO)
    public void handleRollBack(@Payload ResponseCustomerDTO p_Response) throws SubscriberException {
        log.info("Received Customer Info {} ", p_Response.toString());
        try {
            orderService.updateOrder(p_Response);
        } catch (ServiceException e) {
            log.error("Error Update Order {}", e.toString());
        }
    }
}
