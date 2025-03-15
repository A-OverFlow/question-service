package com.example.question_service.answer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnswerCreateDto {

    @NotNull(message = "Question id is mandatory")
    private Long questionId;

    @NotNull(message = "Content is mandatory")
    @NotBlank(message = "Content is mandatory")
    private String content;

    @NotNull(message = "Author is mandatory")
    @NotBlank(message = "Author is mandatory")
    private String author;

    public static AnswerCreateDto from(Long questionId, String content, String author) {
        return AnswerCreateDto.builder()
                .questionId(questionId)
                .content(content)
                .author(author)
                .build();
    }
}
