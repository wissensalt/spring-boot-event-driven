package com.wissensalt.rnd.sbed.oa.endpoint;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.EndPointException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-03
 **/
public interface IOrderEndPoint {

    @PostMapping("/start")
    ResponseEntity startOrder(HttpServletRequest p_HttpServletRequest, @RequestBody RequestTransactionDTO p_Request) throws EndPointException;

    @PostMapping("/finish")
    ResponseEntity finishOrder(HttpServletRequest p_HttpServletRequest, @RequestParam("transaction_code") String p_TransactionCode) throws EndPointException;
}
