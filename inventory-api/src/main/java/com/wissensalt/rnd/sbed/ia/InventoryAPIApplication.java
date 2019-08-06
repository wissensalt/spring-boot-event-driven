package com.wissensalt.rnd.sbed.ia;

import com.wissensalt.rnd.sbed.ia.dao.IInventoryDAO;
import com.wissensalt.rnd.sbed.ia.dao.IInventoryDetailDAO;
import com.wissensalt.rnd.sbed.sd.event.IEventRollBackInput;
import com.wissensalt.rnd.sbed.sd.event.IEventRollBackOutput;
import com.wissensalt.rnd.sbed.sd.event.IEventUpdateCartInput;
import com.wissensalt.rnd.sbed.sd.model.Inventory;
import com.wissensalt.rnd.sbed.sd.model.InventoryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@EnableBinding({
        IEventUpdateCartInput.class,
        IEventRollBackInput.class,
        IEventRollBackOutput.class
})
@EnableJpaRepositories(basePackages = "com.wissensalt.rnd.sbed.ia.dao")
@EntityScan(basePackages = "com.wissensalt.rnd.sbed.sd.model")
@SpringBootApplication(scanBasePackages = {
        "com.wissensalt.rnd.sbed.ia.service",
        "com.wissensalt.rnd.sbed.ia.subscriber",
        "com.wissensalt.rnd.sbed.sd.mapper",
        "com.wissensalt.rnd.sbed.sd.producer",
        "com.wissensalt.rnd.sbed.sd.config",
})
public class InventoryAPIApplication {
    public static void main(String [] args) {
        SpringApplication.run(InventoryAPIApplication.class);
    }
}