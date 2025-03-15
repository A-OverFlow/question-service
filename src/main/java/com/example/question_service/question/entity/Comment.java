package com.example.question_service.question.entity;

import com.example.question_service.answer.entity.Answer;
import com.example.question_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "content", "author"})
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public void updateAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        return id != null && id.equals(((Comment) o).getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.intValue() : 0;
    }

    @Builder
    public Comment(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public static Comment createComment(String content, String author) {
        return Comment.builder()
                .content(content)
                .author(author)
                .build();
    }
}
