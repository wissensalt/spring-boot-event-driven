package com.wissensalt.rnd.sbed.util.messaging;

import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
public interface ITransactionSubscriber<DATA> {

    /**
     * <p>
     *     Receive payload from transaction producer. you can handle it by your own implementation.
     *     Make sure that your implementation can accept same payload (idempotent).
     *     There is possibility transaction payload will be send for multiple times.
     * </p>
     *
     * @see Transactional
     *
     * @param p_Message as payload
     * @throws SubscriberException if any
     */
    @Transactional
    @StreamListener(AppConstant.EventOrderCreated.INPUT_ORDER_CREATED)
    void onMessageArrived(DATA p_Message) throws SubscriberException;
}
