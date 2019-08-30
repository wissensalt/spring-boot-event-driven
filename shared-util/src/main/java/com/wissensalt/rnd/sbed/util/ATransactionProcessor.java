package com.wissensalt.rnd.sbed.util;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.SubscriberException;
import com.wissensalt.rnd.sbed.util.messaging.TransactionReplySender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-26
 **/
@Getter
@Setter
@Service
public abstract class ATransactionProcessor<DATA> implements ITransactionProcessor<DATA> {

    @Autowired
    private TransactionReplySender replySender;

    private boolean status;

    /**
     * <p>
     *     Implementation of transaction in each services.
     *     This method should be Transactional {@link org.springframework.transaction.annotation.Transactional}
     *     When exception occurred {@link SubscriberException} will send information regarding failed transaction
     * </p>
     * @param p_Message as payload
     * @throws SubscriberException if any
     */
    public abstract void handleTransaction(DATA p_Message) throws SubscriberException;

    /**
     * @param p_Message as payload
     * @throws SubscriberException if any exception occurred
     */
    @Override
    public void onMessageArrived(DATA p_Message) throws SubscriberException {
        handleTransaction(p_Message);
        onPostTransaction(p_Message);
    }

    /**
     * <p>
     *     When transaction finished, this method will send success information
     *     to reply channel
     * </p>
     * @param p_Message as payload
     */
    public void onPostTransaction(DATA p_Message) {
        replySender.send((RequestTransactionDTO) p_Message, getServiceName(), isStatus());
    }

    /**
     *
     * @return name of service to remark source of transaction when sending reply channel
     */
    public abstract String getServiceName();
}
