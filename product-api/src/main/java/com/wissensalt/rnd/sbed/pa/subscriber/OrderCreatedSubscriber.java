package com.wissensalt.rnd.sbed.pa.subscriber;

import com.wissensalt.rnd.sbed.pa.service.IProductService;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.sd.producerrollback.RollBackProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
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
public class OrderCreatedSubscriber {

    private final IProductService productService;
    private final RollBackProducer rollBackProducer;

    @StreamListener(AppConstant.EventOrderCreated.INPUT_ORDER_CREATED)
    public void validateProduct(@Payload RequestTransactionDTO p_Request) throws SubscriberException {
        log.info("Received Transaction {} ", p_Request.toString());
        RequestRollBackUpdateCartDTO requestRollBack = new RequestRollBackUpdateCartDTO(p_Request.getTransactionCode(), PRODUCT_API);
        try {
            if (productService.isValidProducts(p_Request.getTransactionCode(), p_Request.getOrder().getOrderDetails())) {
                log.info("All Products are valid");
            } else {
                log.warn("One or more products are not valid");
                rollBackProducer.sendRollBackInformation(requestRollBack);
            }
        } catch (ServiceException e) {
            log.error("An Error Occurred when checking Product validity");
            rollBackProducer.sendRollBackInformation(requestRollBack);
        }
    }
}
