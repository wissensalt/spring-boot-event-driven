package com.wissensalt.rnd.sbed.sd.audit;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

/**
 * created on 1/18/2017
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class ApplicationAuditorAware implements AuditorAware<String>, Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -8684447538387983727L;

    @Override
    public Optional<String> getCurrentAuditor() {
        //Username only for DEMO
        String userName = "ADMIN";
        if (Strings.isBlank(userName)) {
            return Optional.empty();
        } else {
            return Optional.of(userName) ;
        }
    }
}
