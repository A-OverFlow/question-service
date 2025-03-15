package com.example.question_service.answer.entity;

import com.example.question_service.answer.dto.AnswerCreateDto;
import com.example.question_service.answer.dto.AnswerUpdateDto;
import com.example.question_service.answer.dto.AnswerUpdateKey;
import com.example.question_service.common.entity.BaseEntity;
import com.example.question_service.question.entity.Comment;
import com.example.question_service.question.entity.Question;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "content", "author"})
@Table(name = "answer")
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String author;

    @Enumerated(EnumType.STRING)
    private AnswerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void updateAnswer(AnswerUpdateDto answerUpdateDto) {
        AnswerUpdateKey key = AnswerUpdateKey.fromString(answerUpdateDto.getKey());
        String value = answerUpdateDto.getValue();

        if (key.equals(AnswerUpdateKey.CONTENT)) {
            this.content = value;
        } else if (key.equals(AnswerUpdateKey.STATUS)) {
            this.status = AnswerStatus.valueOf(value);
        }
    }

    public void updateStatus(AnswerStatus status) {
        this.status = status;
    }

    public void updateQuestion(Question question) {
        this.question = question;
    }

    public void updateComment(Comment comment) {
        this.comments.add(comment);
        comment.updateAnswer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        return id != null && id.equals(((Answer) o).getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.intValue() : 0;
    }

    @Builder
    public Answer(String content, String author, AnswerStatus status) {
        this.content = content;
        this.author = author;
        this.status = status;
    }

    public static Answer createAnswer(String content, String author, AnswerStatus status) {
        return Answer.builder()
                .content(content)
                .author(author)
                .status(status)
                .build();
    }

    public static Answer createAnswer(AnswerCreateDto answerCreateDto) {
        return Answer.builder()
                .content(answerCreateDto.getContent())
                .author(answerCreateDto.getAuthor())
                .status(AnswerStatus.NOT_ACCEPTED)
                .build();
    }
}
