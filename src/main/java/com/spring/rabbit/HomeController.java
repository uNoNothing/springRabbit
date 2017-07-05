package com.spring.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rabbit.producer.Producer;

@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    Producer producer;

    @GetMapping(value = "/")
    public String home() {

	logger.info("REST /");

	try {
	    producer.sendMessage();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return "check log";

    }

}