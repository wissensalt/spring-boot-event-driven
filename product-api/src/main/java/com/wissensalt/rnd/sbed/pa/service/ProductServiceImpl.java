package com.wissensalt.rnd.sbed.pa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissensalt.rnd.sbed.pa.dao.IProductDAO;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestOrderDetailDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestUpdateEventStateDetailDTO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.model.Product;
import com.wissensalt.rnd.sbed.sd.producerreplyevent.OrderCreatedReplyProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.PAYMENT_API;
import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.PRODUCT_API;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {

    private final IProductDAO productDAO;
    private final ObjectMapper objectMapper;
    private final OrderCreatedReplyProducer replyProducer;

    @Override
    public Boolean isValidProducts(String p_TransactionCode, List<RequestOrderDetailDTO> p_OrderDetails) throws ServiceException {
        boolean result = true;
        List<Long> productIds = new ArrayList<>();
        for (RequestOrderDetailDTO orderDetail : p_OrderDetails) {
            productIds.add(orderDetail.getProductId());
        }
        try {
            if (productIds.size() > 0 && productDAO.countByProductIds(productIds) == productIds.size()) {
                for (Product product : productDAO.findByProductIds(productIds)) {
                    for (RequestOrderDetailDTO orderDetail : p_OrderDetails) {
                        if (Objects.equals(product.getId(), orderDetail.getProductId())) {
                            if (orderDetail.getQuantity() > product.getCurrentStock()) {
                                log.error("Request Quantity > Current Stock for Product {}", product.getId());
                                result = false;
                            } else {
                                product.setTransactionCode(p_TransactionCode);
                                product.setPreviousStock(product.getCurrentStock());
                                product.setCurrentStock(product.getCurrentStock() - orderDetail.getQuantity());
                                productDAO.save(product);
                            }
                            break;
                        }
                    }
                }
            } else {
                result = false;
            }
        } catch (DAOException e) {
            log.error("Error Check Product Validity {}" , e.toString());
            result = false;
        }

        if (result) {
            sendReply(p_TransactionCode);
        }

        return result;
    }

    private void sendReply(String p_TrxCode) {
        RequestUpdateEventStateDetailDTO requestUpdateEvent = new RequestUpdateEventStateDetailDTO();
        try {
            requestUpdateEvent.setPayload(objectMapper.writeValueAsString(p_TrxCode));
        } catch (JsonProcessingException e) {
            log.error("Error convert request to json string {}", e.toString());
        }
        requestUpdateEvent.setStatus(true);
        requestUpdateEvent.setServiceName(PRODUCT_API);
        requestUpdateEvent.setTransactionCode(p_TrxCode);
        try {
            replyProducer.sendReply(requestUpdateEvent);
        } catch (ProducerException e) {
            log.error("Failed to send reply order created {}", e.toString());
        }
    }

    @Override
    public void handleRollback(RequestRollBackUpdateCartDTO p_RequestRollBack) throws ServiceException {
        if (p_RequestRollBack.getRollbackSource().equals(PRODUCT_API)) {
            log.info("Rollback Has been handled");
        } else {
            List<Product> products = new ArrayList<>();
            try {
                products = productDAO.findByTransactionCode(p_RequestRollBack.getTransactionCode());
            } catch (DAOException e) {
                log.error("Error Find Product By Transaction Code");
            }

            for (Product product : products) {
                product.setCurrentStock(product.getPreviousStock());
                productDAO.save(product);
            }
            log.info("Successfull Rollback");
        }
    }
}
