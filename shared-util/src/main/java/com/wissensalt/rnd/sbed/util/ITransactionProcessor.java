package com.wissensalt.rnd.sbed.util;

import com.wissensalt.rnd.sbed.util.messaging.IRollbackSubscriber;
import com.wissensalt.rnd.sbed.util.messaging.ITransactionSubscriber;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-26
 **/
public interface ITransactionProcessor<DATA> extends ITransactionSubscriber<DATA>, IRollbackSubscriber {
}
