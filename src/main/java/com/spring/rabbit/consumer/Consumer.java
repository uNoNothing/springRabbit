package com.spring.rabbit.consumer;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.spring.rabbit.model.Message;

@Component
public class Consumer {
    
    private CountDownLatch latch = new CountDownLatch(1);
    
    public void receiveMessage(String jsonString) throws InterruptedException {

	Gson gson = new Gson();
	Message message = gson.fromJson(jsonString, Message.class);

	System.out.println(LocalDateTime.now().toLocalTime() + " Message received from <" + message.getUsername() + ">");

	Thread.sleep(1000);
	latch.countDown();
    }

    public CountDownLatch getLatch() {
	return latch;
    }

}
