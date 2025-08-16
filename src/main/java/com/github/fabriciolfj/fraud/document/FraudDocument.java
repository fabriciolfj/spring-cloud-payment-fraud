package com.github.fabriciolfj.fraud.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "frauds")
public class FraudDocument {

    @Id
    private String code;
    private int score;
    private LocalDateTime createDate;
}
