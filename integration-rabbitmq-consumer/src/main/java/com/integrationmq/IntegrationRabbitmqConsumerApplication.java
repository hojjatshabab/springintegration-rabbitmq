package com.integrationmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class IntegrationRabbitmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationRabbitmqConsumerApplication.class, args);
    }

}
