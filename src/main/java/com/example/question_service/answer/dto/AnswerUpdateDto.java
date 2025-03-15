package com.example.question_service.answer.dto;

import com.example.question_service.common.validator.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnswerUpdateDto {

    @NotNull(message = "Id is mandatory")
    private Long id;

    @EnumValue(enumClass = AnswerUpdateKey.class, message = "Invalid answer update key", ignoreCase = true)
    private String key;

    @NotNull(message = "Value is mandatory")
    @NotBlank(message = "Value is mandatory")
    private String value;

    public static AnswerUpdateDto from(String key, String value) {
        return AnswerUpdateDto.builder()
                .key(key)
                .value(value)
                .build();
    }
}
