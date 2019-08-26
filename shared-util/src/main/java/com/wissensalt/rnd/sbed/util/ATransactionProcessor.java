package com.wissensalt.rnd.sbed.util;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    protected ObjectMapper mapper;
    private boolean status;

    public abstract void handleTransaction(DATA p_Message) throws SubscriberException;

    @Override
    public void onMessageArrived(DATA p_Message) throws SubscriberException {
        handleTransaction(p_Message);
        onPostTransaction(p_Message);
    }

    public void onPostTransaction(DATA p_Message) {
        replySender.send((RequestTransactionDTO) p_Message, getServiceName(), isStatus());
    }

    public abstract String getServiceName();
}
