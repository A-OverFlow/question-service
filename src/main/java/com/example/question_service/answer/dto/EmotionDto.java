package com.example.question_service.answer.dto;

import com.example.question_service.answer.entity.Emoji;
import com.example.question_service.answer.entity.Emotion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmotionDto {

    private Long id;
    private String author;
    private Emoji emoji;

    public static EmotionDto from(Emotion emotion) {
        return EmotionDto.builder()
                .id(emotion.getId())
                .author(emotion.getAuthor())
                .emoji(emotion.getEmoji())
                .build();
    }
}
