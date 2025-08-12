package com.github.fabriciolfj.fraud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fabriciolfj.fraud.util.ValidationUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class PaymentRequest extends ValidationUtil<PaymentRequest> {

    @NotBlank(message = "{payment.code.notBlank}")
    private String code;

    @Positive(message = "{payment.amount.positive}")
    @NotNull(message = "{payment.amount.notNull}")
    private BigDecimal amount;

    @NotBlank(message = "{payment.cardToken.notBlank}")
    private String cardToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @NotNull(message = "{payment.dateTime.notNull}")
    private LocalDateTime date;

    @NotBlank(message = "{payment.customerId.notBlank}")
    private String customerId;

    public boolean isMinorHours(final int value) {
        return getHours() < value;
    }

    public boolean isMinorAmount(final BigDecimal amount) {
        return this.amount.compareTo(amount) <= 0;
    }

    private int getHours() {
        return this.date.getHour();
    }
}
