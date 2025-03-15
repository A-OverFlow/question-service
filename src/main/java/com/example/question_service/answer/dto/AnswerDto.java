package com.example.question_service.answer.dto;

import com.example.question_service.answer.entity.Answer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnswerDto {

    private Long id;
    private String content;
    private String author;

    public static AnswerDto from(Long id, String content, String author) {
        return AnswerDto.builder()
                .id(id)
                .content(content)
                .author(author)
                .build();
    }

    public static AnswerDto from(Answer answer) {
        return AnswerDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .author(answer.getAuthor())
                .build();
    }
}
