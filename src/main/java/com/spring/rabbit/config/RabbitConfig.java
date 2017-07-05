package com.spring.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.rabbit.consumer.Consumer;

@Configuration
public class RabbitConfig {

    @Value("${queueName}")
    String queueName;

    @Bean
    public ConnectionFactory connectionFactory() {
	CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
	connectionFactory.setUsername("guest");
	connectionFactory.setPassword("guest");
	return connectionFactory;
    }

    @Bean
    Queue queue() {
	return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
	return new TopicExchange("spring-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
	return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	    MessageListenerAdapter listenerAdapter) {
	SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	container.setConnectionFactory(connectionFactory);
	container.setQueueNames(queueName);
	container.setMessageListener(listenerAdapter);
	return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Consumer consumer) {
	return new MessageListenerAdapter(consumer, "receiveMessage");
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
	RabbitTemplate template = new RabbitTemplate(connectionFactory());
	return template;
    }

}
