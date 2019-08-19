package com.wissensalt.rnd.sbed.sd.event.input;

import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IEventBroadcastRollBackInput {
    @Input(AppConstant.EventBroadcastRollback.INPUT_BROADCAST_ROLLBACK)
    SubscribableChannel inBoundRollBackEveryThing();
}
