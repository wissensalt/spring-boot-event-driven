package com.wissensalt.rnd.sbed.sd.event.output;

import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-14
 **/
public interface IEventReplyTransactionOutput {
    @Output(AppConstant.EventReplyTransaction.OUTPUT_REPLY_TRANSACTION)
    MessageChannel outBondReplyTransaction();
}
