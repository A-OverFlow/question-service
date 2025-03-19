package com.example.question_service.answer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmotionRemoveDto {

    @NotNull(message = "Answer id is mandatory")
    private Long answerId;

    @NotNull(message = "Answer id is mandatory")
    @NotBlank(message = "Answer id is mandatory")
    private String author;
}
