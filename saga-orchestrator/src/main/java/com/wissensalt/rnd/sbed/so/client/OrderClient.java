package com.wissensalt.rnd.sbed.so.client;

import com.telkomsigma.kf.oa.shared.data.dto.request.RequestFindByIdDTO;
import com.telkomsigma.kf.oa.shared.data.dto.response.ResponseMasterAddressDTO;
import com.telkomsigma.kf.oa.util.exception.ServiceException;
import com.telkomsigma.kf.oa.util.feign.FeignBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-07-15
 **/
@Service
public class VillageRestClient {

    @Value("${master.api.base-path}")
    private String masterApiBasePath;

    public ResponseMasterAddressDTO findAddressByVillageId(RequestFindByIdDTO<Long> p_RequestAPILogDTO) throws ServiceException {
        IFinishOrderClient villageClient = FeignBuilderFactory.createClient(IFinishOrderClient.class, masterApiBasePath);
        return villageClient.findAddressByVillageId(p_RequestAPILogDTO);
    }
}
