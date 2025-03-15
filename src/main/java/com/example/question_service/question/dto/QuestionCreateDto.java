package com.example.question_service.question.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionCreateDto {

    @NotBlank(message = "Subject is mandatory")
    private String subject;

    @NotBlank(message = "Content is mandatory")
    private String content;

    @NotBlank(message = "Author is mandatory")
    @Size(max = 50, message = "Author must be less than 20 characters")
    private String author;

    public static QuestionCreateDto from(String subject, String content, String author) {
        return QuestionCreateDto.builder()
                .subject(subject)
                .content(content)
                .author(author)
                .build();
    }
}
