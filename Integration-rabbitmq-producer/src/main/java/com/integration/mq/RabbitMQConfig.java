package com.integration.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

	private final ConnectionFactory rabbitConnectionFactory;

	@Bean
	TopicExchange worksExchange() {
		return new TopicExchange("request.exchange", true, false);
	}

	@Bean
	public RabbitTemplate worksRabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
		rabbitTemplate.setExchange("request.exchange");
		rabbitTemplate.setRoutingKey("work");
		rabbitTemplate.setConnectionFactory(rabbitConnectionFactory);
		return rabbitTemplate;
	}

}
