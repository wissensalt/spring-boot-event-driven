package com.wissensalt.rnd.sbed.util.messaging;

import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
public abstract class AProducer<DATA> implements IProducer<DATA> {
    @Override
    public void produceMessage(DATA p_DATA) throws ProducerException {
        getMessageChannel().send(MessageBuilder
                .withPayload(p_DATA)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    protected abstract MessageChannel getMessageChannel();
}
