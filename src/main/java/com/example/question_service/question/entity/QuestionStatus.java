package com.example.question_service.question.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum QuestionStatus {

    NEW("new"),
    ING("ing"),
    DONE("done");

    private final String status;

    public static QuestionStatus fromString(String status) {
        return Arrays.stream(QuestionStatus.values())
                .filter(s -> s.getStatus().equals(status))
                .findAny().orElse(null);
    }
}
