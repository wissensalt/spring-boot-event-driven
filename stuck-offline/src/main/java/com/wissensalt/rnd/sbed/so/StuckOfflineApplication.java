package com.wissensalt.rnd.sbed.so;

import com.wissensalt.rnd.sbed.sd.event.input.IEventRollBackInput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-09-02
 **/
@EnableTransactionManagement
@EnableBinding({
        IEventRollBackInput.class,
})
@EntityScan(basePackages = "com.wissensalt.rnd.sbed.sd.model")
@SpringBootApplication(scanBasePackages = {
        "com.wissensalt.rnd.sbed.so.service",
        "com.wissensalt.rnd.sbed.so.subscriber",
        "com.wissensalt.rnd.sbed.sd.mapper",
        "com.wissensalt.rnd.sbed.sd.config",
})
public class StuckOfflineApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuckOfflineApplication.class);
    }
}
