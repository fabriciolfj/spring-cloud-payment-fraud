package com.github.fabriciolfj.fraud.service;

import com.github.fabriciolfj.fraud.dto.ResultAssessmentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProcessScoreService {

    public ResultAssessmentResponse execute(final List<Boolean> values, final String code) {
        var result = isTransactionSuccess(values, code);
        log.info("result assessment transaction {}", result);

        return result;
    }

    private ResultAssessmentResponse isTransactionSuccess(List<Boolean> values, String code) {
        if (values.stream().allMatch(it -> it))  {
            return ResultAssessmentResponse.builder()
                    .code(code)
                    .score(100)
                    .build();
        }

        return isTransactionMedian(values, code);
    }

    private ResultAssessmentResponse isTransactionMedian(List<Boolean> values, String code) {
        if (values.stream().anyMatch(it -> it))  {
            return ResultAssessmentResponse.builder()
                    .code(code)
                    .score(50)
                    .build();
        }

        return isTransactionFail(values, code);
    }

    private ResultAssessmentResponse isTransactionFail(List<Boolean> values, String code) {
        return ResultAssessmentResponse.builder()
                .code(code)
                .score(0)
                .build();
    }
}
