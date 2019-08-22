package com.wissensalt.rnd.sbed.util.messaging;

import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
public interface IRollbackSubscriber {

    @Transactional
    @StreamListener(AppConstant.EventRollBack.INPUT_ROLLBACK)
    void handleRollBack(@Payload RequestRollBackDTO p_Request) throws SubscriberException;
}
