package com.micropizzeria.marketplace.controller;

import com.micropizzeria.marketplace.application.OrderService;
import com.micropizzeria.marketplace.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/status/{orderId}")
    public ResponseEntity<String> getOrderStatus(@PathVariable UUID orderId) {
        var notification = orderService.getOrderStatus(orderId);
        if (notification.getResult() == null) {
            return new ResponseEntity<>(notification.getErrors().getFirst(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(notification.getResult());
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {

        return ResponseEntity.ok(new Object() {
            public final String status = "OK";
            public final String message = "order api is up and running!";
        });
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        var notification = orderService.placeOrder(order);
        var orderId = notification.getResult();
        if (orderId != null) {
            return new ResponseEntity<>(orderId, HttpStatus.ACCEPTED);
        }

        if (notification.getErrors().contains("Payment")) {
            return new ResponseEntity<>(notification.getErrors().getFirst(), HttpStatus.PAYMENT_REQUIRED);
        }

        return new ResponseEntity<>(notification.getErrors().getFirst(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
