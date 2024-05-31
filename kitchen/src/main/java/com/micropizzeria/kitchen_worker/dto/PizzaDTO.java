package com.micropizzeria.kitchen_worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO {

    private Long uuid;

    private String flavor;

    private int count;
}