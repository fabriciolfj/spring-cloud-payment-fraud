package com.github.fabriciolfj.fraud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultValidation {

    private boolean validationHours;
    private PaymentRequest request;


    public String getCode() {
        return request.getCode();
    }
}
