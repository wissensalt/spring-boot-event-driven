package com.wissensalt.rnd.sbed.oa.client;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-07-15
 **/
@Slf4j
@Service
public class InventoryRestClient {

    @Value("${inventory.api.base-path}")
    private String inventoryAPIBasePath;

    public Response conductTransaction(RequestTransactionDTO p_Request) throws ServiceException {
        log.info("Sending trx info to inventory");
        IInventoryClient inventoryClient = FeignBuilderFactory.createClient(IInventoryClient.class, inventoryAPIBasePath);
        return inventoryClient.conductTransaction(p_Request);
    }
}
