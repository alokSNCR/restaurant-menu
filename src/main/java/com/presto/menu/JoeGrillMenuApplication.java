package com.presto.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class JoeGrillMenuApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(JoeGrillMenuApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(JoeGrillMenuApplication.class, args);
	}

}
