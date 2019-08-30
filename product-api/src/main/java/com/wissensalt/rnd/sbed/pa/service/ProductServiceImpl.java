package com.wissensalt.rnd.sbed.pa.service;

import com.wissensalt.rnd.sbed.pa.dao.IProductDAO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestOrderDetailDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.model.Product;
import com.wissensalt.rnd.sbed.util.messaging.TransactionReplySender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private final TransactionReplySender transactionReplySender;

    @Override
    public Boolean isValidProducts(RequestTransactionDTO p_Request) throws ServiceException {
        boolean result = true;
        List<Long> productIds = new ArrayList<>();
        for (RequestOrderDetailDTO orderDetail : p_Request.getOrder().getOrderDetails()) {
            productIds.add(orderDetail.getProductId());
        }
        try {
            if (productIds.size() > 0 && productDAO.countByProductIds(productIds) == productIds.size()) {
                for (Product product : productDAO.findByProductIds(productIds)) {
                    for (RequestOrderDetailDTO orderDetail : p_Request.getOrder().getOrderDetails()) {
                        if (Objects.equals(product.getId(), orderDetail.getProductId())) {
                            if (orderDetail.getQuantity() > product.getCurrentStock()) {
                                log.error("Request Quantity > Current Stock for Product {}", product.getId());
                                result = false;
                            } else {
                                product.setTransactionCode(p_Request.getTransactionCode());
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
            transactionReplySender.send(p_Request, PRODUCT_API, true);
        }

        return result;
    }

    @Override
    public void handleRollback(RequestRollBackDTO p_RequestRollBack) throws ServiceException {
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
