package com.wissensalt.rnd.sbed.ia.endpoint;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.EndPointException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-09-02
 **/
@RequestMapping("/inventory")
public interface IInventoryEndPoint {

    @PostMapping("/start")
    ResponseEntity startInventory(HttpServletRequest p_Request, @RequestBody RequestTransactionDTO p_RequestTransactionDTO) throws EndPointException;
}
