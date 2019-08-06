package com.wissensalt.rnd.sbed.sd.mapper;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;
import com.wissensalt.rnd.sbed.sd.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

/**
 * Created on 1/8/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
@Mapper(config = MappingConfig.class)
public abstract class OrderMapper {

    @Mappings({
            @Mapping(target = "transactionCode", source = "requestTransactionDTO.transactionCode"),
            @Mapping(target = "totalPrice", source = "requestTransactionDTO.order.totalPrice"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdOn", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedOn", ignore = true),
            @Mapping(target = "customerId", ignore = true),
            @Mapping(target = "orderDetails", ignore = true)
    })
    public abstract Order toOrderModel(RequestTransactionDTO requestTransactionDTO);

//    @IterableMapping(elementTargetType = OrderDetail.class)
//    protected abstract Set<OrderDetail> mapOrderDetails(List<RequestOrderDetailDTO> orderDetails);

//    protected String mapAuthorityToString(Authority authority) {
//        return authority.getValue();
//    }
}
