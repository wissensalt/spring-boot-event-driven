package com.wissensalt.rnd.sbed.ca;

import com.wissensalt.rnd.sbed.sd.event.input.GreetingStreamInput;
import com.wissensalt.rnd.sbed.sd.event.input.IEventRollBackInput;
import com.wissensalt.rnd.sbed.sd.event.input.IEventUpdateCartInput;
import com.wissensalt.rnd.sbed.sd.event.output.IEventCustomerInfoOutput;
import com.wissensalt.rnd.sbed.sd.event.output.IEventRollBackOutput;
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
        GreetingStreamInput.class,
        IEventRollBackOutput.class,
        IEventUpdateCartInput.class,
        IEventCustomerInfoOutput.class
})
@EnableJpaRepositories(basePackages = "com.wissensalt.rnd.sbed.ca")
@EntityScan(basePackages = "com.wissensalt.rnd.sbed.sd.model")
@SpringBootApplication(scanBasePackages = {
        "com.wissensalt.rnd.sbed.ca.service",
        "com.wissensalt.rnd.sbed.ca.producer",
        "com.wissensalt.rnd.sbed.ca.subscriber",
        "com.wissensalt.rnd.sbed.sd.mapper",
        "com.wissensalt.rnd.sbed.sd.producer",
        "com.wissensalt.rnd.sbed.sd.config",
})
public class CustomerAPIApplication {
    public static void main(String [] args) {
        SpringApplication.run(CustomerAPIApplication.class);
    }
}
