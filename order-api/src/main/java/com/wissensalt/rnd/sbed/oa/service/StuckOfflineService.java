package com.wissensalt.rnd.sbed.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.wissensalt.rnd.sbed.config.AsynchronousTaskConfiguration;
import com.wissensalt.rnd.sbed.oa.dao.IStuckOfflineDAO;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;

@Service
public class StuckOfflineService implements IStuckOfflineService {
	
	@Autowired
	IStuckOfflineDAO stuckOfflineDao;

	@Override
	@Async(AsynchronousTaskConfiguration.TASK_EXECUTOR_STUCK_OFFLINE)
	public void insertFailedRequest(final RequestTransactionDTO failedRequestDto) {
		// TODO Auto-generated method stub
		try {
			stuckOfflineDao.insert(failedRequestDto);
		} catch(Exception e) {
			// log this
		}
	}
	
}
