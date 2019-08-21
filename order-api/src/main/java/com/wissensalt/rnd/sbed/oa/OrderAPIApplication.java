package com.wissensalt.rnd.sbed.oa;

import com.wissensalt.rnd.sbed.sd.event.input.IEventCustomerInfoInput;
import com.wissensalt.rnd.sbed.sd.event.input.IEventOrderCreatedReplyInput;
import com.wissensalt.rnd.sbed.sd.event.input.IEventRollBackInput;
import com.wissensalt.rnd.sbed.sd.event.input.IEventOrderCreatedInput;
import com.wissensalt.rnd.sbed.sd.event.output.GreetingStreamOutput;
import com.wissensalt.rnd.sbed.sd.event.output.IEventOrderCreatedReplyOutput;
import com.wissensalt.rnd.sbed.sd.event.output.IEventRollBackOutput;
import com.wissensalt.rnd.sbed.sd.event.output.IEventOrderCreatedOutput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@EnableTransactionManagement
@EnableBinding({
        GreetingStreamOutput.class,
        IEventOrderCreatedOutput.class,
        IEventOrderCreatedReplyInput.class,
        IEventRollBackInput.class,
        IEventRollBackOutput.class,
        IEventCustomerInfoInput.class,
})
@EnableJpaRepositories(basePackages = "com.wissensalt.rnd.sbed.oa.dao")
@EntityScan(basePackages = "com.wissensalt.rnd.sbed.sd.model")
@SpringBootApplication(scanBasePackages = {
        "com.wissensalt.rnd.sbed.oa.service",
        "com.wissensalt.rnd.sbed.oa.endpoint",
        "com.wissensalt.rnd.sbed.oa.validation",
        "com.wissensalt.rnd.sbed.oa.producer",
        "com.wissensalt.rnd.sbed.oa.subscriber",
        "com.wissensalt.rnd.sbed.sd.mapper",
        "com.wissensalt.rnd.sbed.sd.producerevent",
        "com.wissensalt.rnd.sbed.sd.producerrollback",
        "com.wissensalt.rnd.sbed.sd.config",
})
public class OrderAPIApplication {
    public static void main(String [] args) {
        SpringApplication.run(OrderAPIApplication.class);
    }
}
