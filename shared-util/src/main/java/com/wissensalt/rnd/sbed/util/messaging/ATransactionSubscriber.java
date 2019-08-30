package com.wissensalt.rnd.sbed.util.messaging;

import org.springframework.integration.annotation.MessageEndpoint;

/**
 *
 * <p>
 *     Useful when you want to subscribe rollback channel
 *     {@link com.wissensalt.rnd.sbed.sd.constval.AppConstant.EventOrderCreated} in generic way
 * </p>
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
@MessageEndpoint
public abstract class ATransactionSubscriber<DATA> implements ITransactionSubscriber<DATA> {
}
