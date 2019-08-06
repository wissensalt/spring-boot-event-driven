package com.wissensalt.rnd.sbed.oa.validation;

import com.wissensalt.rnd.sbed.oa.dao.IOrderDAO;
import com.wissensalt.rnd.sbed.oa.service.IOrderService;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.exception.DAOException;
import com.wissensalt.rnd.sbed.sd.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class OrderValidator {

    private final IOrderDAO orderDAO;

    public Boolean validate(RequestTransactionDTO p_Request) throws ServiceException {
        try {
            return !Objects.isNull(p_Request.getTransactionCode()) && Objects.isNull(orderDAO.findByTransactionCode(p_Request.getTransactionCode()));
        } catch (DAOException e) {
            return false;
        }
    }
}
