package com.wissensalt.rnd.sbed.oa.service;

import com.wissensalt.rnd.sbed.oa.dao.IEventStateDAO;
import com.wissensalt.rnd.sbed.oa.dao.IEventStateDetailDAO;
import com.wissensalt.rnd.sbed.oa.producer.EventOrderProducer;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestUpdateEventStateDetailDTO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.model.EventState;
import com.wissensalt.rnd.sbed.sd.model.EventStateDetail;
import com.wissensalt.rnd.sbed.sd.producerrollback.RollBackProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.ORDER_API;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-14
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class SagaServiceImpl implements ISagaService {
    private final IEventStateDAO eventStateDAO;
    private final IEventStateDetailDAO eventStateDetailDAO;
    private final EventOrderProducer eventOrderProducer;
    private final RollBackProducer rollBackProducer;
    private static final List<String> DEPENDENCY_PROCESS = Arrays.asList("INVENTORY", "PRODUCT", "PAYMENT");

    @Transactional
    public void broadcastOrderTransaction(RequestTransactionDTO p_Request) throws ServiceException {
        EventState eventState = new EventState();
        eventState.setTransactionCode(p_Request.getTransactionCode());
        eventState.setTransactionStatus(false);
        this.eventStateDAO.save(eventState);
        RequestRollBackUpdateCartDTO requestRollBack = new RequestRollBackUpdateCartDTO(p_Request.getTransactionCode(), ORDER_API);

        try {
            this.eventOrderProducer.sendUpdateCart(p_Request);
        } catch (ProducerException e) {
            log.error("Error Broadcast Order Message to Broker : {}", e.toString());
            this.rollBackProducer.sendRollBackInformation(requestRollBack);
            throw new ServiceException("Error Broadcast Order Message to Broker");
        }
    }

    @Transactional
    public void updateEventStateDetail(RequestUpdateEventStateDetailDTO p_Request) throws ServiceException {
        log.info("UPDS {}", p_Request.toString());
        if (!p_Request.getServiceName().equals(ORDER_API)) {
            EventState eventState = null;

            try {
                eventState = this.eventStateDAO.findByTransactionCode(p_Request.getTransactionCode());
            } catch (DAOException e) {
                log.error("Error Find Event State By Transaction Code {} : {}", p_Request.getTransactionCode(), e.toString());
            }

            if (!Objects.isNull(eventState)) {
                EventStateDetail eventStateDetail = null;

                try {
                    eventStateDetail = this.eventStateDetailDAO.findByTransactionCodeAndServiceName(p_Request.getTransactionCode(), p_Request.getServiceName());
                } catch (DAOException e) {
                    log.error("Error find Event state detail by transaction code {} and Service Name {} : {}", p_Request.getTransactionCode(), p_Request.getServiceName(), e.toString());
                }

                if (Objects.isNull(eventStateDetail)) {
                    eventStateDetail = new EventStateDetail();
                    eventStateDetail.setTransactionCode(p_Request.getTransactionCode());
                    eventStateDetail.setServiceName(p_Request.getServiceName());
                }

                eventStateDetail.setStatus(p_Request.getStatus());
                eventStateDetail.setRemarks(p_Request.getRemarks());
                this.eventStateDetailDAO.save(eventStateDetail);
                boolean passed = true;

                try {
                    List<EventStateDetail> details = this.eventStateDetailDAO.findByTransactionCode(p_Request.getTransactionCode());

                    for (EventStateDetail esd : details) {
                        if (!DEPENDENCY_PROCESS.contains(esd.getServiceName())) {
                            passed = false;
                            break;
                        }
                    }
                } catch (DAOException e) {
                    log.error("Error check required state : {}", e.toString());
                }

                if (passed) {
                    log.info("transaction finished");
                    eventState.setTransactionStatus(true);
                    eventStateDAO.save(eventState);
                    //TODO order completed
                    // Send message to KAFKA for AO
                    // update event published completed at

                }
            } else {
                log.error("Event State with Transaction Code {} is not Found", p_Request.getTransactionCode());
            }
        } else {
            log.warn("Nothing todo here... source is from saga it self!");
        }

    }

    public void handleRollback(RequestRollBackUpdateCartDTO p_Request) throws ServiceException {
    }
}
