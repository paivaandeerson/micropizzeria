package com.micropizzeria.kitchen_worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private UUID uuid;
    private String cardNumber;
    private String expiryMonth;
    private String cardholderName;
    private String cardSecurityCode;
}