package com.wissensalt.rnd.sbed.sd.constval;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface AppConstant {
    interface EventUpdateCart {
        String INPUT_UPDATE_CART = "input-update-cart";
        String OUTPUT_UPDATE_CART = "output-update-cart";
    }

    interface EventRollBack {
        String INPUT_ROLLBACK = "input-rollback";
        String OUTPUT_ROLLBACK = "output-rollback";
    }

    interface EventCustomerInfo {
        String INPUT_CUSTOMER_INFO = "input-customer-info";
        String OUTPUT_CUSTOMER_INFO = "output-customer-info";
    }

    interface ServiceName {
        String ORDER_API = "ORDER-API";
        String CUSTOMER_API = "CUSTOMER-API";
        String PRODUCT_API = "PRODUCT-API";
        String INVENTORY_API = "INVENTORY-API";
        String PAYMENT_API =  "PAYMENT-API";
    }
}
