package com.wissensalt.rnd.sbed.sd.constval;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface AppConstant {
    interface EventOrderCreated {
        String INPUT_ORDER_CREATED = "input-order-created";
        String OUTPUT_ORDER_CREATED = "output-order-created";
    }

    interface EventOrderCreatedReply {
        String INPUT_ORDER_CREATED_REPLY = "input-order-created-reply";
        String OUTPUT_ORDER_CREATED_REPLY = "output-order-created-reply";
    }

    interface EventRollBack {
        String INPUT_ROLLBACK = "input-rollback";
        String OUTPUT_ROLLBACK = "output-rollback";
    }

    interface EventCustomerInfo {
        String INPUT_CUSTOMER_INFO = "input-customer-info";
        String OUTPUT_CUSTOMER_INFO = "output-customer-info";
    }

    interface EventBroadcastRollback {
        String INPUT_BROADCAST_ROLLBACK = "input-broadcast-rollback";
        String OUTPUT_BROADCAST_ROLLBACK = "output-broadcast-rollback";
    }

    interface EventReplyTransaction {
        String INPUT_REPLY_TRANSACTION = "input-reply-transaction";
        String OUTPUT_REPLY_TRANSACTION = "output-reply-transaction";
    }

    interface ServiceName {
        String ORDER_API = "ORDER-API";
        String SAGA_ORCHESTRATOR = "SAGA-ORCHESTRATOR";
        String CUSTOMER_API = "CUSTOMER-API";
        String PRODUCT_API = "PRODUCT-API";
        String INVENTORY_API = "INVENTORY-API";
        String PAYMENT_API =  "PAYMENT-API";
    }
}
