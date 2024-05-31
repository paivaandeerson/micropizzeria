package com.micropizzeria.marketplace.application;

import com.micropizzeria.marketplace.domain.model.Order;
import com.micropizzeria.marketplace.domain.repository.OrderRepository;
import com.micropizzeria.marketplace.domain.repository.PaymentRepository;
import com.micropizzeria.marketplace.domain.repository.StatusOrderRepository;
import com.micropizzeria.marketplace.infrastructure.RabbitMQProducer;
import com.micropizzeria.marketplace.sdk.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final StatusOrderRepository statusOrderRepository;
    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        PaymentRepository paymentRepository,
                        StatusOrderRepository statusOrderRepository,
                        RabbitMQProducer rabbitMQProducer) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.statusOrderRepository = statusOrderRepository;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public Notification<UUID> placeOrder(Order order) {
        var notification = new Notification<UUID>();

        if (order.getPizzas().isEmpty()) {
            notification.addError("At least 1 pizza is required");
            return notification;
        }

        if (paymentRepository.processPayment(order)) {
            //should use SAGA
            orderRepository.save(order);
            rabbitMQProducer.sendMessage(order);
            notification.setResult(order.getUuid());
            return notification;
        }

        notification.addError("Payment not approved");
        return notification;
    }

    public Notification<String> getOrderStatus(UUID orderId) {
        var notification = new Notification<String>();
        var status = statusOrderRepository.getStatus(orderId);

        var toUpdate = orderRepository.findById(orderId).orElse(null);

        if (toUpdate != null) {
            toUpdate.setStatus(status);
            orderRepository.save(toUpdate);
            notification.setResult(status);
        } else {
            notification.addError("Order not found");
        }

        return notification;
    }
}