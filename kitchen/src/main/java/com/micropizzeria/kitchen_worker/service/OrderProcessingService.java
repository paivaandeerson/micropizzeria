package com.micropizzeria.kitchen_worker.service;

import com.micropizzeria.kitchen_worker.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingService {

    @Autowired
    private EventProducer eventProducer;

    public void processOrder(String order) throws InterruptedException {
            // Simulate processing time
            Thread.sleep(60000);
            eventProducer.sendMessage("order-confirmed-topic", "Order confirmed");

            // Simulate additional processing time
            Thread.sleep(60000);
            eventProducer.sendMessage("ready-to-delivery-topic", "Ready to delivery");


    }
}