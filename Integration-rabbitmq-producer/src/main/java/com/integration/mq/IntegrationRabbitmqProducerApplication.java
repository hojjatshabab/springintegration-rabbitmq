package com.integration.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class IntegrationRabbitmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationRabbitmqProducerApplication.class, args);
    }

}
