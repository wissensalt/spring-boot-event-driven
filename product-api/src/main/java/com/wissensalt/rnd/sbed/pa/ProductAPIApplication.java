package com.wissensalt.rnd.sbed.pa;

import com.wissensalt.rnd.sbed.sd.event.IEventRollBackInput;
import com.wissensalt.rnd.sbed.sd.event.IEventRollBackOutput;
import com.wissensalt.rnd.sbed.sd.event.IEventUpdateCartInput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-04
 **/
@EnableBinding({
        IEventUpdateCartInput.class,
        IEventRollBackOutput.class,
        IEventRollBackInput.class
})
@EnableJpaRepositories(basePackages = "com.wissensalt.rnd.sbed.pa.dao")
@EntityScan(basePackages = "com.wissensalt.rnd.sbed.sd.model")
@SpringBootApplication(scanBasePackages = {
        "com.wissensalt.rnd.sbed.pa.service",
        "com.wissensalt.rnd.sbed.pa.subscriber",
        "com.wissensalt.rnd.sbed.sd.producer",
        "com.wissensalt.rnd.sbed.sd.config",
})
public class ProductAPIApplication {

    public static void main(String [] args) {
        SpringApplication.run(ProductAPIApplication.class);
    }
}
