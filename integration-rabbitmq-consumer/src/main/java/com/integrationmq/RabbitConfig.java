package com.integrationmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final ConnectionFactory rabbitConnectionFactory;

    @Bean
    public Exchange requestExchange() {
        return ExchangeBuilder.topicExchange("request.exchange")
                .durable(true)
                .build();
    }

    @Bean
    public Queue requestQueue() {
        return QueueBuilder.durable("request")
                .build();
    }

    @Bean
    Binding requestBinding() {
        return BindingBuilder
                .bind(requestQueue())
                .to(requestExchange()).with("#").noargs();
    }


    @Bean
    public SimpleMessageListenerContainer workListenerContainer() {
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(rabbitConnectionFactory);
        container.setQueues(requestQueue());
        container.setConcurrentConsumers(2);
        container.setDefaultRequeueRejected(false);
        return container;
    }


}
