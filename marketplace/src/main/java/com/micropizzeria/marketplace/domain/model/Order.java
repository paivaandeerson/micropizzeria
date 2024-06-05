package com.micropizzeria.marketplace.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name="`status`")
    private String status;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pizza> pizzas;
}