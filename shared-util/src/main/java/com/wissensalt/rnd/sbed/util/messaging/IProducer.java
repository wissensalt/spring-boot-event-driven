package com.wissensalt.rnd.sbed.util.messaging;

import com.wissensalt.rnd.sbed.sd.exception.ProducerException;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
public interface IProducer<DATA> {

    void produceMessage(DATA p_DATA) throws ProducerException;
}
