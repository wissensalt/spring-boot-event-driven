package com.wissensalt.rnd.sbed.sd.event;

import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IEventRollBackInput {
    @Input(AppConstant.EventRollBack.INPUT_ROLLBACK)
    SubscribableChannel inBoundRollBackEveryThing();
}
