package com.micropizzeria.marketplace.domain.repository;

import com.micropizzeria.marketplace.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findById(UUID id);
}