package com.github.fabriciolfj.fraud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.fabriciolfj.fraud.util.ValidationUtil;
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

    @Positive(message = "")
    private BigDecimal amount;
    private String cardToken;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private String customerId;
}
