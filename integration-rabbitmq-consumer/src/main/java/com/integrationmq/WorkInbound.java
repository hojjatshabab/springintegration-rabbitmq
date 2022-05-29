package com.integrationmq;

import com.integrationmq.domain.WorkUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;


@Configuration
public class WorkInbound {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Bean
    public IntegrationFlow inboundFlow() {
        return IntegrationFlows.from(
                Amqp.inboundAdapter(rabbitConfig.workListenerContainer()))
                .transform(Transformers.fromJson(WorkUnit.class))
                .log()
                .filter("(headers['x-death'] != null) ? headers['x-death'][0].count <= 3: true", f -> f.discardChannel("nullChannel"))
                .handle("workHandler", "process")
                .get();
    }

}
