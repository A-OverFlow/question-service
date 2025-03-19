package com.example.question_service.answer.dto;

import com.example.question_service.question.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDto {

    private Long id;
    private String content;
    private String author;

    public static CommentDto from(Long id, String content, String author) {
        return CommentDto.builder()
                .id(id)
                .content(content)
                .author(author)
                .build();
    }

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .build();
    }
}
