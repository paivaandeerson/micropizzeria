package com.micropizzeria.status_order_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class StatusOrderConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatusOrderConsumerApplication.class, args);
	}
}