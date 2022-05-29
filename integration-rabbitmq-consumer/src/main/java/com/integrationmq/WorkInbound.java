package com.integrationmq;

import com.integrationmq.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;


@Configuration
@RequiredArgsConstructor
public class WorkInbound {

    private final RabbitConfig rabbitConfig;
    @Bean
    public IntegrationFlow inboundFlow() {
        return IntegrationFlows.from(
                Amqp.inboundAdapter(rabbitConfig.workListenerContainer()))
                .transform(Transformers.fromJson(Transaction.class))
                .channel(requests())
                .log()
                .get();
    }


    @Bean
    public QueueChannel requests() {
        return new QueueChannel();
    }

    /*@Bean
    public IntegrationFlow inboundFlow(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from(
                Amqp.inboundAdapter(connectionFactory, "requests"))
                .transform(Transformers.fromJson(Transaction.class))
                .channel(requests())
                .log()
                .get();
    }*/

}
