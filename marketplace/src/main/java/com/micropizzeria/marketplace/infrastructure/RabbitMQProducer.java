package com.micropizzeria.marketplace.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropizzeria.marketplace.domain.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    @Value("#{T(Boolean).parseBoolean('${feature-toggle.kitchen-mq}')}")
    private Boolean useMQ;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public void sendMessage(Order order) {
        if (!useMQ)
            return;

        try {
            String orderJson = objectMapper.writeValueAsString(order);
            rabbitTemplate.convertAndSend(exchange, routingkey, orderJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
    }
}