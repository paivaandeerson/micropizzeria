package com.micropizzeria.kitchen_worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private UUID uuid;

    private String status;

    private List<PizzaDTO> pizzas;
}