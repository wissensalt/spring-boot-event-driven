package com.wissensalt.rnd.sbed.sd.config;

import com.wissensalt.rnd.sbed.sd.audit.ApplicationAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-06
 **/
@EnableJpaAuditing(auditorAwareRef = "applicationAuditorAware")
@Configuration
public class AuditConfig {
    @Bean
    public AuditorAware<String> applicationAuditorAware() {
        return new ApplicationAuditorAware();
    }
}
