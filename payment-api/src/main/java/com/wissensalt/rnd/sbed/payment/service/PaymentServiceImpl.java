package com.wissensalt.rnd.sbed.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissensalt.rnd.sbed.payment.dao.IPaymentDAO;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestReplyTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ProducerException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.mapper.PaymentMapper;
import com.wissensalt.rnd.sbed.sd.model.Payment;
import com.wissensalt.rnd.sbed.util.messaging.TransactionReplySender;
import com.wissensalt.rnd.sbed.util.producerreplyevent.OrderCreatedReplyProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.wissensalt.rnd.sbed.sd.constval.AppConstant.ServiceName.PAYMENT_API;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-06
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class PaymentServiceImpl implements IPaymentService{

    private final IPaymentDAO paymentDAO;
    private final PaymentMapper paymentMapper;
    private final TransactionReplySender transactionReplySender;

    @Transactional
    @Override
    public void conductPayment(RequestTransactionDTO p_Request) throws ServiceException {
        try {
            if (Objects.isNull(paymentDAO.findByTransactionCode(p_Request.getTransactionCode()))) {
                Payment payment = paymentMapper.toPaymentModel(p_Request);
                paymentDAO.save(payment);
                log.info("Success Conduct Payment");
            } else {
                log.warn("Previous transaction already finished");
            }
        } catch (DAOException e) {
            log.error("Error Conduct payment");
        }

        transactionReplySender.send(p_Request, PAYMENT_API, true);
    }

    @Transactional
    @Override
    public void handleRollBack(RequestRollBackDTO p_Request) throws ServiceException {
        Payment payment = null;
        try {
            payment = paymentDAO.findByTransactionCode(p_Request.getTransactionCode());
        } catch (DAOException e) {
            log.error("Error Find Payment By Transaction Code {}", p_Request.getTransactionCode());
        }
        if (!Objects.isNull(payment)) {
            paymentDAO.delete(payment);
            log.info("Success Handling Rollback");
        }
    }
}
