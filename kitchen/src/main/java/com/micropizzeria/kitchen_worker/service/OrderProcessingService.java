package com.micropizzeria.kitchen_worker.service;

import com.micropizzeria.kitchen_worker.dto.OrderDTO;
import com.micropizzeria.kitchen_worker.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingService {

    @Autowired
    private EventProducer eventProducer;

    public void processOrder(OrderDTO order) throws InterruptedException {

        System.out.println("Order received:" + order.getUuid());
        // Simulate cooking time
        Thread.sleep(30000);
        eventProducer.sendMessage("order-confirmed-topic", "Order confirmed");
        System.out.println("an event was dispatched in order-confirmed-topic");


        // Simulate additional processing time
        Thread.sleep(30000);
        eventProducer.sendMessage("order-finished-topic", "Ready to delivery");
        System.out.println("Ready to deliver" + order.getUuid());
        System.out.println("an event was dispatched in order-finished-topic");
    }
}