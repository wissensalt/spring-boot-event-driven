package com.wissensalt.rnd.sbed.oa.service;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;

public interface IStuckOfflineService {
	void insertFailedRequest(RequestTransactionDTO failedRequestDto);
}
