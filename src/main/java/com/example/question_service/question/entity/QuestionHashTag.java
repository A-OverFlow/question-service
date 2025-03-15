package com.example.question_service.question.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionHashTag {

    @Id
    @Column(name = "question_hash_tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hash_tag_id")
    private HashTag hashTag;

    @Builder
    public QuestionHashTag(Question question, HashTag hashTag) {
        this.question = question;
        this.hashTag = hashTag;
    }

    public static QuestionHashTag createQuestionHashTag(Question question, HashTag hashTag) {
        return QuestionHashTag.builder()
                .question(question)
                .hashTag(hashTag)
                .build();
    }

    public void updateQuestion(Question question) {
        this.question = question;
    }

    public void updateHashTag(HashTag hashTag) {
        this.hashTag = hashTag;
    }
}
