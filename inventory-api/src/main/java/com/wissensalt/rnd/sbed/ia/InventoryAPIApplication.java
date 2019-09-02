package com.wissensalt.rnd.sbed.ia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.wissensalt.rnd.sbed.ia.dao")
@EntityScan(basePackages = "com.wissensalt.rnd.sbed.sd.model")
@SpringBootApplication(scanBasePackages = {
        "com.wissensalt.rnd.sbed.ia.service",
        "com.wissensalt.rnd.sbed.sd.mapper",
        "com.wissensalt.rnd.sbed.sd.config",
})
public class InventoryAPIApplication {
    public static void main(String [] args) {
        SpringApplication.run(InventoryAPIApplication.class);
    }
}
