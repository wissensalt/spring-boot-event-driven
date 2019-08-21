package com.wissensalt.rnd.sbed.ca.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissensalt.rnd.sbed.ca.dao.ICustomerDAO;
import com.wissensalt.rnd.sbed.ca.producer.CustomerProducer;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestUpdateEventStateDetailDTO;
import com.wissensalt.rnd.sbed.sd.dto.response.ResponseCustomerDTO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.model.Customer;
import com.wissensalt.rnd.sbed.sd.producerreplyevent.OrderCreatedReplyProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.CUSTOMER_API;
import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.INVENTORY_API;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService{

    private final ICustomerDAO customerDAO;
    private final CustomerProducer customerProducer;
    private final OrderCreatedReplyProducer replyProducer;
    private final ObjectMapper objectMapper;

    @Override
    public void handleRollBack(String p_TransactionId) throws ServiceException {
        log.info("Nothing Todo Here... Rollback. Customer service is not mandatory process");
    }

    @Transactional
    @Override
    public void handleCustomer(RequestTransactionDTO p_Request) throws ServiceException {
        Customer customer = null;
        try {
            customer = customerDAO.findByUserName(p_Request.getCustomer().getName());
        } catch (DAOException e) {
            log.error("Error Find Customer By User Name {}", e.toString());
        }

        if (!Objects.isNull(customer)) {
            log.info("Customer Already Exist");
        } else {
            log.info("Customer is not Found. {} will be inserted as new Customer", p_Request.getCustomer().getName());
            customer = new Customer();
            customer.setUserName(p_Request.getCustomer().getName());
            customerDAO.save(customer);
        }

        try {
            customerProducer.sendCustomerInformation(new ResponseCustomerDTO(p_Request.getTransactionCode(), customer.getId()));
            log.info("Success Send Customer Info to Kafka Broker");
        } catch (ProducerException e) {
            log.error("Error Sending Customer Information to Kafka Broker {}", e.toString());
        }

        sendReply(p_Request);
    }

    private void sendReply(RequestTransactionDTO p_Request) {
        RequestUpdateEventStateDetailDTO requestUpdateEvent = new RequestUpdateEventStateDetailDTO();
        try {
            requestUpdateEvent.setPayload(objectMapper.writeValueAsString(p_Request));
        } catch (JsonProcessingException e) {
            log.error("Error convert request to json string {}", e.toString());
        }
        requestUpdateEvent.setStatus(true);
        requestUpdateEvent.setServiceName(CUSTOMER_API);
        requestUpdateEvent.setTransactionCode(p_Request.getTransactionCode());
        try {
            replyProducer.sendReply(requestUpdateEvent);
        } catch (ProducerException e) {
            log.error("Failed to send reply order created {}", e.toString());
        }
    }
}
