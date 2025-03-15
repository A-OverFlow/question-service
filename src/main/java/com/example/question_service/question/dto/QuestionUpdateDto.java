package com.example.question_service.question.dto;

import com.example.question_service.common.validator.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionUpdateDto {

    @NotNull(message = "Id is mandatory")
    private Long id;

    @EnumValue(enumClass = QuestionUpdateKey.class, message = "Invalid question update key", ignoreCase = true)
    private String key;

    @NotNull(message = "Value is mandatory")
    @NotBlank(message = "Value is mandatory")
    private String value;

    public static QuestionUpdateDto from(String key, String value) {
        return QuestionUpdateDto.builder()
                .key(key)
                .value(value)
                .build();
    }
}
