package com.wissensalt.rnd.sbed.util.messaging;

import org.springframework.integration.annotation.MessageEndpoint;

/**
 * <p>
 *     Useful when you want to subscribe rollback channel
 *     {@link com.wissensalt.rnd.sbed.sd.constval.AppConstant.EventRollBack} in generic way
 * </p>
 * Dangling service must listen to rollback channel using this class.
 *
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
@MessageEndpoint
public abstract class ARollbackSubscriber implements IRollbackSubscriber {
}
