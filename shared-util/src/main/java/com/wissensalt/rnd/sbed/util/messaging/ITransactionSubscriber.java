package com.wissensalt.rnd.sbed.util.messaging;

import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
public interface ITransactionSubscriber<DATA> {

    @Transactional
    @StreamListener(AppConstant.EventOrderCreated.INPUT_ORDER_CREATED)
    void conductTransaction(Message<DATA> message) throws SubscriberException;
}
