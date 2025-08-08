package com.github.fabriciolfj.fraud.service;

import com.github.fabriciolfj.fraud.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValidateHoursTransactionService {

    @Value("${limit.hours}")
    private int limitHours;

    public boolean execute(final PaymentRequest request) {
        return request.isMinorHours(limitHours);
    }
}
