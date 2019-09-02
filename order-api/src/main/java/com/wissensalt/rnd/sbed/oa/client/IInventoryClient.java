package com.wissensalt.rnd.sbed.oa.client;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import feign.Headers;
import feign.RequestLine;
import feign.Response;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-07-15
 **/
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface IInventoryClient {

    @RequestLine("POST /inventory/start")
    Response conductTransaction(RequestTransactionDTO p_Request) throws ServiceException;
}
