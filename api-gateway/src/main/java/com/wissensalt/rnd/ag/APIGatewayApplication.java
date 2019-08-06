package com.wissensalt.rnd.ag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-06
 **/
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class APIGatewayApplication {

    public static void main(String [] args) {
        SpringApplication.run(APIGatewayApplication.class);
    }
}
