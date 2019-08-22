package com.wissensalt.rnd.sbed.sd.event.input;

import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.event.IInboundEvent;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
public interface IEventOrderCreatedReplyInput extends IInboundEvent {
    @Input(AppConstant.EventOrderCreatedReply.INPUT_ORDER_CREATED_REPLY)
    @Override
    SubscribableChannel inboundMessage();
}
