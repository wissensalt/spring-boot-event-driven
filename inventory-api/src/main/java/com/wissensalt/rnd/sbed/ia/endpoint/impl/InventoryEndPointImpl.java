package com.wissensalt.rnd.sbed.ia.endpoint.impl;

import com.wissensalt.rnd.sbed.ia.endpoint.IInventoryEndPoint;
import com.wissensalt.rnd.sbed.ia.service.IInventoryService;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.EndPointException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-09-02
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryEndPointImpl implements IInventoryEndPoint {

    private final IInventoryService inventoryService;

    @Override
    public ResponseEntity startInventory(HttpServletRequest p_Request, RequestTransactionDTO p_RequestTransactionDTO) throws EndPointException {
        try {
            inventoryService.conductTransaction(p_RequestTransactionDTO);
            return ResponseEntity.ok("Success");
        } catch (ServiceException e) {
            log.error("Failed to conduct transaction {}", e.toString());
            return new ResponseEntity<>("Failed to conduct transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
