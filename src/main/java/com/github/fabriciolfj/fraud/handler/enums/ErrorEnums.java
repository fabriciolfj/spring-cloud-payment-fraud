package com.github.fabriciolfj.fraud.handler.enums;

import java.util.ResourceBundle;

public enum ErrorEnums {

    FRAUD_NOT_FOUND;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("exceptions/messages");
        return bundle.getString(this.name() + ".message");
    }
}
