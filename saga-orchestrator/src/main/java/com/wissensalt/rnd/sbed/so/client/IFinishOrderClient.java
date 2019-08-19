package com.wissensalt.rnd.sbed.so.client;

import com.telkomsigma.kf.oa.shared.data.dto.request.RequestFindByIdDTO;
import com.telkomsigma.kf.oa.shared.data.dto.response.ResponseMasterAddressDTO;
import com.telkomsigma.kf.oa.util.exception.ServiceException;
import feign.Headers;
import feign.RequestLine;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-07-15
 **/
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface IVillageClient {

    @RequestLine("POST /api/village/find-address")
    ResponseMasterAddressDTO findAddressByVillageId(RequestFindByIdDTO<Long> p_RequestAPILogDTO) throws ServiceException;
}
