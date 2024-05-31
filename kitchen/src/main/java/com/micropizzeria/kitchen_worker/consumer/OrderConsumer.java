package com.micropizzeria.kitchen_worker.consumer;

import com.micropizzeria.kitchen_worker.service.OrderProcessingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @Autowired
    private OrderProcessingService orderProcessingService;

    @RabbitListener(queues = "order-queue")
    public void consumeOrder(String message) throws InterruptedException {
        //TODO deserialize json message
        orderProcessingService.processOrder(message);
    }
}