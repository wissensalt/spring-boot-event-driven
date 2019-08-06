package com.wissensalt.rnd.sbed.payment;

import com.wissensalt.rnd.sbed.sd.event.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-06
 **/
@EnableTransactionManagement
@EnableBinding({
        GreetingStreamOutput.class,
        IEventUpdateCartInput.class,
        IEventRollBackInput.class,
        IEventRollBackOutput.class,
})
@EnableJpaRepositories(basePackages = "com.wissensalt.rnd.sbed.payment.dao")
@EntityScan(basePackages = "com.wissensalt.rnd.sbed.sd.model")
@SpringBootApplication(scanBasePackages = {
        "com.wissensalt.rnd.sbed.payment.service",
        "com.wissensalt.rnd.sbed.payment.subscriber",
        "com.wissensalt.rnd.sbed.sd.mapper",
        "com.wissensalt.rnd.sbed.sd.producer",
})
public class PaymentAPIApplication {
    public static void main(String [] args) {
        SpringApplication.run(PaymentAPIApplication.class);
    }
}
