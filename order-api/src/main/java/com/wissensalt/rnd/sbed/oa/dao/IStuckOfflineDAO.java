package com.wissensalt.rnd.sbed.oa.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wissensalt.rnd.sbed.sd.dto.request.RequestTransactionDTO;

@Repository
public interface IStuckOfflineDAO extends MongoRepository<RequestTransactionDTO, String> {
	
}
