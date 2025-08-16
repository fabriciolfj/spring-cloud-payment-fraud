package com.github.fabriciolfj.fraud.handler.exceptions;

import com.github.fabriciolfj.fraud.handler.enums.ErrorEnums;

public class FraudNotFoundException extends RuntimeException {

    public FraudNotFoundException() {
        super(ErrorEnums.FRAUD_NOT_FOUND.getMessage());
    }
}
