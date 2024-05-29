package com.micropizzeria.marketplace.domain.repository;

import com.micropizzeria.marketplace.domain.model.Order;

public interface PaymentRepository {
    boolean processPayment(Order order);
}