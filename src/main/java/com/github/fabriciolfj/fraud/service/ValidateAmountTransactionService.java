package com.github.fabriciolfj.fraud.service;

import com.github.fabriciolfj.fraud.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValidateAmountTransactionService {

    @Value("${limit.amount}")
    private BigDecimal limitAmount;

    public boolean execute(final PaymentRequest request) {
        return request.isMinorAmount(limitAmount);
    }
}
