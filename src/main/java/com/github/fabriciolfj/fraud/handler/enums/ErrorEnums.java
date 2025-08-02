package com.github.fabriciolfj.fraud.handler.enums;

import java.util.ResourceBundle;

public enum ErrorEnums {

    CUSTOMER_NOT_FOUND;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("exceptions/message");
        return bundle.getString(this.name() + ".message");
    }
}
