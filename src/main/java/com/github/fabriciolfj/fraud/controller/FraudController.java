package com.github.fabriciolfj.fraud.controller;

import com.github.fabriciolfj.fraud.document.FraudDocument;
import com.github.fabriciolfj.fraud.service.FraudService;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/frauds")
public class FraudController {

    private final FraudService fraudService;

    @GetMapping("/{code}")
    public FraudDocument getFraud(@PathVariable @NotEmpty final String code) {
        return fraudService.getFraud(code);
    }
}
