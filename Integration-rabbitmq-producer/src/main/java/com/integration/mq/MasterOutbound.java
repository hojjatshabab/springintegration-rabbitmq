package com.integration.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;


@Configuration
@RequiredArgsConstructor
public class MasterOutbound {

    private final RabbitMQConfig rabbitMQConfig;

    @Bean
    public DirectChannel requests() {
        return new DirectChannel();
    }
    @Bean
    public IntegrationFlow outboundFlow(AmqpTemplate amqpTemplate) {
        return IntegrationFlows
                .from(requests())
                .transform(Transformers.toJson())
                .handle(Amqp.outboundAdapter(amqpTemplate)
                        .routingKey("requests"))
                .get();
    }


   /* @Bean
    public IntegrationFlow toOutboundQueueFlow() {
        return IntegrationFlows.from(requests())
                .transform(Transformers.toJson())
                .handle(Amqp.outboundAdapter(rabbitMQConfig.worksRabbitTemplate()))
                .get();
    }*/

}