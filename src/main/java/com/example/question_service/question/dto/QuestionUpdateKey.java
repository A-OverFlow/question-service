package com.example.question_service.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum QuestionUpdateKey {

    SUBJECT("subject"),
    CONTENT("content"),
    STATUS("status");

    private final String key;

    public static QuestionUpdateKey fromString(String key) {
        return Arrays.stream(QuestionUpdateKey.values())
                .filter(k -> k.getKey().equals(key))
                .findAny().orElse(null);
    }
}
