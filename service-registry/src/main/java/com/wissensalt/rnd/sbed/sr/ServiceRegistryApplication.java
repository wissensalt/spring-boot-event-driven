package com.wissensalt.rnd.sbed.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-06
 **/
@EnableEurekaServer
@SpringBootApplication
public class ServiceRegistryApplication {
    public static void main(String [] args) {
        SpringApplication.run(ServiceRegistryApplication.class);
    }
}
