package com.wissensalt.rnd.sbed.sd.event.input;

import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.event.IInboundEvent;
import com.wissensalt.rnd.sbed.sd.event.IOutboundEvent;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IEventCustomerInfoInput extends IInboundEvent {

    @Input(AppConstant.EventCustomerInfo.INPUT_CUSTOMER_INFO)
    @Override
    SubscribableChannel inboundMessage();
}
