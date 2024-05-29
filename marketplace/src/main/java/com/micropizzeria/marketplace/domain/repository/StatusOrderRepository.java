package com.micropizzeria.marketplace.domain.repository;

import com.micropizzeria.marketplace.domain.model.Order;

import java.util.UUID;

public interface StatusOrderRepository {
    String getStatus(UUID orderId);
}
