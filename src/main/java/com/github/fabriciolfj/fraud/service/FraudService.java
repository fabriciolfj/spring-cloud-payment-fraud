package com.github.fabriciolfj.fraud.service;

import com.github.fabriciolfj.fraud.document.FraudDocument;
import com.github.fabriciolfj.fraud.handler.exceptions.FraudNotFoundException;
import com.github.fabriciolfj.fraud.repositories.FraudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Slf4j
public class FraudService {

    private final FraudRepository fraudRepository;

    @Transactional
    public void saveDocument(final FraudDocument fraudDocument) {
        fraudRepository.save(fraudDocument);
    }

    @Transactional(readOnly = true)
    public FraudDocument getFraud(final String code) {
        log.info("get fraude by code {}", code);
        return fraudRepository.findByCode(code).orElseThrow(FraudNotFoundException::new);
    }
}
