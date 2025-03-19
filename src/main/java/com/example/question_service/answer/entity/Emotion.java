package com.example.question_service.answer.entity;

import com.example.question_service.answer.dto.EmotionCreateDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "emoji", "author"})
@Table(name = "emotion")
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    @Enumerated(EnumType.STRING)
    private Emoji emoji;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public void updateEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    public void updateAnswer(Answer answer) {
        this.answer = answer;
    }

    @Builder
    public Emotion(Emoji emoji, String author) {
        this.emoji = emoji;
        this.author = author;
    }

    public static Emotion from(Emoji emoji, String author) {
        return Emotion.builder()
                .emoji(emoji)
                .author(author)
                .build();
    }

    public static Emotion from(EmotionCreateDto emotionCreateDto) {
        Emoji emoji = Emoji.fromString(emotionCreateDto.getEmoji());

        return Emotion.builder()
                .emoji(emoji)
                .author(emotionCreateDto.getAuthor())
                .build();
    }
}
