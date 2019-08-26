package com.wissensalt.rnd.sbed.util.messaging;

import com.wissensalt.rnd.sbed.sd.event.IOutboundEvent;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

/**
 * <p>
 *      Useful when you need to send message into kafka broker with payload JSON.
 *      You just need to provide {@link MessageChannel}.
 *      For more organized {@code MessageChannel} , every event output should extend from {@link com.wissensalt.rnd.sbed.sd.event.IOutboundEvent}
 *      Outbound channel is dynamic, you can define it by your own where you want o publish the message
 * </p>
 *
 * @see MessageHeaders#CONTENT_TYPE
 * @see MimeTypeUtils#APPLICATION_JSON
 * @see IOutboundEvent#outboundMessage()
 *
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
public abstract class AProducer<DATA> implements IProducer<DATA> {

    /**
     * @param p_DATA as payload from producer
     * @throws ProducerException if any exception happened
     */
    @Override
    public void produceMessage(DATA p_DATA) throws ProducerException {
        getOutboundMessageChannel().send(MessageBuilder
                .withPayload(p_DATA)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    protected abstract MessageChannel getOutboundMessageChannel();
}
