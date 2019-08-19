package com.wissensalt.rnd.sbed.so;

import com.wissensalt.rnd.sbed.sd.event.input.IEventRollBackInput;
import com.wissensalt.rnd.sbed.sd.event.input.IEventUpdateCartInput;
import com.wissensalt.rnd.sbed.sd.event.output.IEventRollBackOutput;
import com.wissensalt.rnd.sbed.sd.event.output.IEventUpdateCartOutput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-14
 **/
@EnableTransactionManagement
@EnableBinding({
        IEventUpdateCartOutput.class,
        IEventUpdateCartInput.class,
        IEventRollBackInput.class,
        IEventRollBackOutput.class,
})
@EnableJpaRepositories(basePackages = "com.wissensalt.rnd.sbed.so.dao")
@EntityScan(basePackages = "com.wissensalt.rnd.sbed.sd.model")
@SpringBootApplication(scanBasePackages = {
        "com.wissensalt.rnd.sbed.so.client",
        "com.wissensalt.rnd.sbed.so.service",
        "com.wissensalt.rnd.sbed.so.endpoint",
        "com.wissensalt.rnd.sbed.so.producer",
        "com.wissensalt.rnd.sbed.so.subscriber",
        "com.wissensalt.rnd.sbed.sd.mapper",
        "com.wissensalt.rnd.sbed.sd.producer",
        "com.wissensalt.rnd.sbed.sd.config",
})
public class SagaOrchestrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SagaOrchestrationApplication.class);
    }
}
