package com.wissensalt.rnd.sbed.ia.service.impl;

import com.wissensalt.rnd.sbed.ia.dao.IInventoryDAO;
import com.wissensalt.rnd.sbed.ia.dao.IInventoryDetailDAO;
import com.wissensalt.rnd.sbed.ia.service.IInventoryService;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestOrderDetailDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.mapper.InventoryDetailMapper;
import com.wissensalt.rnd.sbed.sd.mapper.InventoryMapper;
import com.wissensalt.rnd.sbed.sd.model.Inventory;
import com.wissensalt.rnd.sbed.sd.model.InventoryDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.INVENTORY_API;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class InventoryServiceImpl implements IInventoryService {

    private final InventoryDetailMapper inventoryDetailMapper;
    private final InventoryMapper inventoryMapper;
    private final IInventoryDAO inventoryDAO;
    private final IInventoryDetailDAO inventoryDetailDAO;

    @Transactional
    @Override
    public void conductTransaction(RequestTransactionDTO p_Request) throws ServiceException {
        Inventory inventory = inventoryDAO.save(inventoryMapper.toInventoryModel(p_Request));

        List<InventoryDetail> inventoryDetailList = new ArrayList<>();
        for (RequestOrderDetailDTO orderDetail : p_Request.getOrder().getOrderDetails()) {
            InventoryDetail inventoryDetail = inventoryDetailMapper.toInventoryDetail(orderDetail);
            inventoryDetail.setInventory(inventory);

            inventoryDetailList.add(inventoryDetailDAO.save(inventoryDetail));
        }
        inventoryDetailDAO.saveAll(inventoryDetailList);
        log.info("Success Conduct Update Cart Transaction");
    }

    @Transactional
    @Override
    public void handleRollBack(RequestRollBackUpdateCartDTO p_Request) throws ServiceException {
        try {
            if (!p_Request.getRollbackSource().equals(INVENTORY_API)) {
                List<InventoryDetail> inventoryDetails = inventoryDetailDAO.findByInventory_TransactionCode(p_Request.getTransactionCode());
                if (inventoryDetails.size() > 0) {
                    inventoryDetailDAO.deleteAll(inventoryDetails);
                }
                Inventory inventory = inventoryDAO.findByTransactionCode(p_Request.getTransactionCode());
                if (!Objects.isNull(inventory)) {
                    inventoryDAO.delete(inventory);
                }
                log.info("Success Handle RollBack");
            }else {
                log.info("Rollback Has Been Handled");
            }
        } catch (DAOException e) {
            log.error("Error Save Handle RollBack Inventory {}", e.toString());
        }
    }
}
