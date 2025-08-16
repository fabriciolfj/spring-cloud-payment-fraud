package com.github.fabriciolfj.fraud.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.fraud.dto.PaymentRequest;
import com.github.fabriciolfj.fraud.dto.ResultAssessmentResponse;
import com.github.fabriciolfj.fraud.dto.ResultValidation;
import com.github.fabriciolfj.fraud.handler.exceptions.FailDeserializationException;
import com.github.fabriciolfj.fraud.service.FraudService;
import com.github.fabriciolfj.fraud.service.ProcessScoreService;
import com.github.fabriciolfj.fraud.service.ValidateAmountTransactionService;
import com.github.fabriciolfj.fraud.service.ValidateHoursTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Function;

import static com.github.fabriciolfj.fraud.listener.ResultAssessmentResponseMapper.toDocument;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentListener {

    private final ValidateHoursTransactionService validateHoursTransactionService;
    private final ValidateAmountTransactionService validateAmountTransactionService;
    private final ProcessScoreService processScoreService;
    private final ObjectMapper mapper;
    private final FraudService fraudService;

    @Bean
    public Function<String, ResultValidation> hours() {
        return value -> {
            try {
                final PaymentRequest request = mapper.readValue(value, PaymentRequest.class);
                var result = validateHoursTransactionService.execute(request);

                return new ResultValidation(result, request);
            } catch (JsonProcessingException e) {
                log.error("fail deserialization details {}", e.getMessage());
                throw new FailDeserializationException();
            }
        };
    }

    @Bean
    public Function<ResultValidation, ResultAssessmentResponse> amount() {
        return value -> {
            var result = validateAmountTransactionService.execute(value.getRequest());
            var resultScore = processScoreService.execute(List.of(value.isValidationHours(), result), value.getCode());

            fraudService.saveDocument(toDocument(resultScore));
            return resultScore;
        };
    }
}
