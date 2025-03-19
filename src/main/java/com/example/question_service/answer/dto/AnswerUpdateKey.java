package com.example.question_service.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnswerUpdateKey {

    CONTENT("content"),
    STATUS("status");

    private final String key;

    public static AnswerUpdateKey fromString(String key) {
        for (AnswerUpdateKey answerUpdateKey : AnswerUpdateKey.values()) {
            if (answerUpdateKey.getKey().equalsIgnoreCase(key)) {
                return answerUpdateKey;
            }
        }
        return null;
    }
}
