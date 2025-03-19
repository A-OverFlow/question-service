package com.example.question_service.answer.dto;

import com.example.question_service.answer.entity.Emoji;
import com.example.question_service.common.validator.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmotionCreateDto {

    @NotNull(message = "Answer id is mandatory")
    private Long answerId;

    @NotNull(message = "Answer id is mandatory")
    @NotBlank(message = "Answer id is mandatory")
    private String author;

    @EnumValue(enumClass = Emoji.class, message = "Invalid emoji", ignoreCase = true)
    private String emoji;

    public static EmotionCreateDto from(String author, String emoji) {
        return EmotionCreateDto.builder()
                .author(author)
                .emoji(emoji)
                .build();
    }
}
