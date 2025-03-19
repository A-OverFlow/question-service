package com.example.question_service.answer.dto;

import com.example.question_service.answer.entity.Answer;
import com.example.question_service.answer.entity.AnswerStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AnswerDto {

    private Long id;
    private String content;
    private String author;
    private AnswerStatus status;
    private List<CommentDto> commentDtoList;
    private List<EmotionDto> emotionDtoList;

    public static AnswerDto from(Long id, String content, String author) {
        return AnswerDto.builder()
                .id(id)
                .content(content)
                .author(author)
                .build();
    }

    public static AnswerDto from(Answer answer) {
        List<CommentDto> commentDtoList = answer.getComments().stream()
                .map(CommentDto::from)
                .toList();

        List<EmotionDto> emotionDtoList = answer.getEmotions().stream()
                .map(EmotionDto::from)
                .toList();

        return AnswerDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .author(answer.getAuthor())
                .commentDtoList(commentDtoList)
                .emotionDtoList(emotionDtoList)
                .build();
    }
}
