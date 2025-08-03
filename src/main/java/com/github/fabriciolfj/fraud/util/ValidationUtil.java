package com.github.fabriciolfj.fraud.util;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

public abstract class ValidationUtil<T> {

    private Validator validator;

    public ValidationUtil() {
        this.validator = buildDefaultValidatorFactory().getValidator();
    }

    public void setValidator() {
        var violation = validator.validate(this);

        if (violation.isEmpty()) {
            return;
        }

        throw new ConstraintViolationException(violation);
    }
}
