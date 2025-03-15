package com.example.question_service.question.dto;

import com.example.question_service.answer.dto.AnswerDto;
import com.example.question_service.question.entity.Question;
import com.example.question_service.question.entity.QuestionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuestionDto {

    private Long id;
    private String subject;
    private String content;
    private String author;
    private QuestionStatus status;
    private List<AnswerDto> answers;

    public static QuestionDto from(Long id, String subject, String content, String author, QuestionStatus status, List<AnswerDto> answers) {
        return QuestionDto.builder()
                .id(id)
                .subject(subject)
                .content(content)
                .author(author)
                .status(status)
                .answers(answers)
                .build();
    }

    public static QuestionDto from(Question question) {
        List<AnswerDto> answerDtoList = question.getAnswers().stream()
                .map(AnswerDto::from)
                .toList();

        return QuestionDto.builder()
                .id(question.getId())
                .subject(question.getSubject())
                .content(question.getContent())
                .author(question.getAuthor())
                .status(question.getStatus())
                .answers(answerDtoList)
                .build();
    }
}
