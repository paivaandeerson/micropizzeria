package com.micropizzeria.kitchen_worker.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue orderQueue() {
        return new Queue("orderQueue", false);
    }
}