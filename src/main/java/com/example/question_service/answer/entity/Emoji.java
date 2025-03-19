package com.example.question_service.answer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Emoji {

    LIKE("like"),
    DISLIKE("dislike"),
    LOVE("love"),
    ANGRY("angry"),
    SAD("sad");

    private final String emoji;

    public static Emoji fromString(String emoji) {
        return Arrays.stream(Emoji.values())
                .filter(e -> e.getEmoji().equalsIgnoreCase(emoji))
                .findAny().orElse(null);
    }
}
