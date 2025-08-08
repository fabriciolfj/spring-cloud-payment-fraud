package com.github.fabriciolfj.fraud.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.fraud.dto.PaymentRequest;
import com.github.fabriciolfj.fraud.dto.ResultAssessmentResponse;
import com.github.fabriciolfj.fraud.handler.exceptions.FailDeserializationException;
import com.github.fabriciolfj.fraud.service.ProcessScoreService;
import com.github.fabriciolfj.fraud.service.ValidateAmountTransactionService;
import com.github.fabriciolfj.fraud.service.ValidateHoursTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;
import java.util.function.Function;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentListener {

    private final ValidateHoursTransactionService validateHoursTransactionService;
    private final ValidateAmountTransactionService validateAmountTransactionService;
    private final ProcessScoreService processScoreService;
    private final ObjectMapper mapper;

    @Bean
    public Function<String, Tuple2<Boolean, PaymentRequest>> hours() {
        return value -> {
            try {
                final PaymentRequest request = mapper.readValue(value, PaymentRequest.class);
                var result = validateHoursTransactionService.execute(request);

                return Tuples.of(result, request);
            } catch (JsonProcessingException e) {
                log.error("fail deserialization details {}", e.getMessage());
                throw new FailDeserializationException();
            }
        };
    }

    @Bean
    public Function<Tuple2<Boolean, PaymentRequest>, ResultAssessmentResponse> amount() {
        return value -> {
            var result = validateAmountTransactionService.execute(value.getT2());
            var assessments = List.of(value.getT1(), result);

            return processScoreService.execute(assessments, value.getT2().getCode());
        };
    }
}
