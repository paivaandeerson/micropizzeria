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
@Table(name = "payments")

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String cardNumber;
    private String expiryMonth;
    private String cardholderName;
    private String cardSecurityCode;

}
