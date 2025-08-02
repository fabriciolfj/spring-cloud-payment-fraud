package com.github.fabriciolfj.fraud.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class PaymentRequest {

    @Positive(message = "")
    private BigDecimal amount;
    private String cardToken;
    private String customerId;
    private String orderId;
}
