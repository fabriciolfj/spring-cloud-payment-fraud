package com.github.fabriciolfj.fraud.repositories;

import com.github.fabriciolfj.fraud.document.FraudDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FraudRepository extends MongoRepository<FraudDocument, String> {

    Optional<FraudDocument> findByCode(final String code);
}
