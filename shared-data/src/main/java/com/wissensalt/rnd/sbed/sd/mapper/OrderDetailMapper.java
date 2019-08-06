package com.wissensalt.rnd.sbed.sd.mapper;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestOrderDetailDTO;
import com.wissensalt.rnd.sbed.sd.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@Component
@Mapper(config = MappingConfig.class)
public abstract class OrderDetailMapper {

    @Mappings({
            @Mapping(target = "productId", source = "request.productId"),
            @Mapping(target = "quantity", source = "request.quantity"),
            @Mapping(target = "price", source = "request.price"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdOn", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedOn", ignore = true),
            @Mapping(target = "order", ignore = true)
    })
    public abstract OrderDetail toOrderDetailModel(RequestOrderDetailDTO request);
}
