package com.wissensalt.rnd.sbed.util.dao;

import com.wissensalt.rnd.sbed.sd.model.HttpLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-22
 **/
public interface IHttpLogDAO extends JpaRepository<HttpLog, Long> {
}
