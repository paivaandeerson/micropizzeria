package com.micropizzeria.marketplace.application;

import com.micropizzeria.marketplace.domain.model.Order;
import com.micropizzeria.marketplace.domain.repository.PaymentRepository;
import com.micropizzeria.marketplace.domain.repository.StatusOrderRepository;
import com.micropizzeria.marketplace.infrastructure.RabbitMQProducer;
import com.micropizzeria.marketplace.sdk.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final PaymentRepository paymentRepository;
    private final StatusOrderRepository statusOrderRepository;
    // TODO: Refactor to use a Kafka for processing orders and payments
    // asynchronously
    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public OrderService(PaymentRepository paymentRepository,
            StatusOrderRepository statusOrderRepository,
            RabbitMQProducer rabbitMQProducer) {
        this.paymentRepository = paymentRepository;
        this.statusOrderRepository = statusOrderRepository;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public Notification<UUID> placeOrder(Order orderCommand) {
        var notification = new Notification<UUID>();

        if (orderCommand.getPizzas().isEmpty()) {
            notification.addError("At least 1 pizza is required");
            return notification;
        }

        if (!paymentRepository.processPayment(orderCommand)) {
            notification.addError("Payment not approved");
            return notification;
        }

        try {
            //TODO: save orderCommand in a persistent storage
            rabbitMQProducer.sendMessage(orderCommand);
            notification.setResult(orderCommand.getUuid());
            System.out.println("order created: " + orderCommand.getUuid());
            return notification;

        } catch (Exception e) {
            // TODO: to implement
            // paymentRepository.rollBack(orderCommand)
            // rabbitMQProducer.rollBack(orderCommand);
            notification.addError("Error processing the order: " + e.getMessage());
        }

        notification.addError("Payment not approved");
        return notification;
    }

    public Notification<String> queryOrderStatus(UUID orderId) {
        var notification = new Notification<String>();
        var statusResult = statusOrderRepository.getStatus(orderId);

        if (statusResult == null || statusResult.isEmpty()) {
            notification.addError("Order not found");
            return notification;
        }

        notification.setResult(statusResult);
        return notification;
    }
}