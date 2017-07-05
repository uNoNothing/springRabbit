package com.spring.rabbit.producer;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.spring.rabbit.consumer.Consumer;
import com.spring.rabbit.model.Message;

@Component
public class Producer {
    
    @Value("${queueName}")
    private String queueName;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Consumer consumer;

    public void sendMessage() throws Exception{

	System.out.println("Sending message...");
	for (int i = 0; i < 10; i++){
	    Message message = new Message("This is some text", "user_" + i, UUID.randomUUID().toString());

	    System.out.println(LocalDateTime.now().toLocalTime() + " Message produced from <" + message.getUsername() + ">");
	    
	    Gson gson = new Gson();
	    String jsonString = gson.toJson(message);
	    
	    rabbitTemplate.convertAndSend(queueName, jsonString);
	}
	
	consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
