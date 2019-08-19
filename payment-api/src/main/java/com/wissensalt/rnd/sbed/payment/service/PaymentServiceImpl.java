package com.wissensalt.rnd.sbed.payment.service;

import com.wissensalt.rnd.sbed.payment.dao.IPaymentDAO;
import com.wissensalt.rnd.sbed.sd.constval.AppConstant;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestRollBackUpdateCartDTO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import com.wissensalt.rnd.sbed.sd.mapper.PaymentMapper;
import com.wissensalt.rnd.sbed.sd.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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
    }

    @Transactional
    @Override
    public void handleRollBack(RequestRollBackUpdateCartDTO p_Request) throws ServiceException {
        if (p_Request.getRollbackSource().equals(AppConstant.ServiceName.PAYMENT_API)) {
            log.warn("Rollback has been handled by exception handler");
        } else {
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
}
