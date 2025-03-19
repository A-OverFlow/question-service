package com.example.question_service.answer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AnswerStatus {

    NOT_ACCEPTED("not accepted"),
    ACCEPTED("accepted");

    private final String status;

    public static AnswerStatus fromString(String status) {
        return Arrays.stream(AnswerStatus.values())
                .filter(s -> s.getStatus().equalsIgnoreCase(status))
                .findAny().orElse(null);
    }
}
