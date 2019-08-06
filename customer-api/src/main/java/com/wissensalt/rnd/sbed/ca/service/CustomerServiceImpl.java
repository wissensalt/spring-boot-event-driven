package com.wissensalt.rnd.sbed.ca.service;

import com.wissensalt.rnd.sbed.ca.dao.ICustomerDAO;
import com.wissensalt.rnd.sbed.ca.producer.CustomerProducer;
import com.wissensalt.rnd.sbed.sd.dto.response.ResponseCustomerDTO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    @Override
    public void handleRollBack(String p_TransactionId) throws ServiceException {
        log.info("Nothing Todo Here... Rollback. Customer service is not mandatory process");
    }

    @Transactional
    @Override
    public void handleCustomer(String p_TransactionCode, String p_CustomerName) throws ServiceException {
        try {
            Customer customer = customerDAO.findByUserName(p_CustomerName);
            if (!Objects.isNull(customer)) {
                log.info("Customer Already Exist");
                throw new ServiceException("CUSTOMER ALREADY EXIST");
            } else {
                log.info("Customer is not Found. {} will be inserted as new Customer", p_CustomerName);
                customer = new Customer();
                customer.setUserName(p_CustomerName);
                customerDAO.save(customer);
            }

            try {
                customerProducer.sendCustomerInformation(new ResponseCustomerDTO(p_TransactionCode, customer.getId()));
                log.info("Success Send Customer Info to Kafka Broker");
            } catch (ProducerException e) {
                log.error("Error Sending Customer Information to Kafka Broker {}", e.toString());
            }

        } catch (DAOException e) {
            log.error("Error Handle Customer {}", e.toString());
        }
    }
}
