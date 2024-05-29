package com.micropizzeria.marketplace.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uuid;

    @Column(name="`flavor`")
    private String flavor;

    @Column(name="`count`")
    private int count;
}