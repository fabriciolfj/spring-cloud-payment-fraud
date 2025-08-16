package com.github.fabriciolfj.fraud.listener;

import com.github.fabriciolfj.fraud.document.FraudDocument;
import com.github.fabriciolfj.fraud.dto.ResultAssessmentResponse;

import java.time.LocalDateTime;

public class ResultAssessmentResponseMapper {

    private ResultAssessmentResponseMapper() { }

    public static FraudDocument toDocument(final ResultAssessmentResponse result) {
        return FraudDocument.builder()
                .code(result.getCode())
                .score(result.getScore())
                .createDate(LocalDateTime.now())
                .build();
    }
}
