package com.micropizzeria.kitchen_worker.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; // importação do Jackson
import com.micropizzeria.kitchen_worker.dto.OrderDTO;
import com.micropizzeria.kitchen_worker.service.OrderProcessingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    public OrderConsumer() {

    }
    @Autowired
    private OrderProcessingService orderProcessingService;

    @RabbitListener(queues = "order-queue")
    public void consumeOrder(String command) throws InterruptedException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var orderDTO = objectMapper.readValue(command, OrderDTO.class);
        orderProcessingService.processOrder(orderDTO);
    }
}