package com.example.question_service.answer.service;

import com.example.question_service.answer.dto.*;
import com.example.question_service.answer.entity.Emoji;
import com.example.question_service.answer.entity.Emotion;
import com.example.question_service.answer.repository.AnswerRepository;
import com.example.question_service.answer.entity.Answer;
import com.example.question_service.question.entity.Question;
import com.example.question_service.question.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public AnswerDto getAnswer(Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found: " + id));

        return AnswerDto.from(answer);
    }

    public List<AnswerDto> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId)
                .stream()
                .map(AnswerDto::from)
                .toList();
    }

    public List<AnswerDto> getAnswersByAuthor(String author) {
        return answerRepository.findByAuthor(author)
                .stream()
                .map(AnswerDto::from)
                .toList();
    }

    @Transactional
    public AnswerDto createAnswer(AnswerCreateDto answerCreateDto) {
        Question question = questionRepository.findById(answerCreateDto.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found: " + answerCreateDto.getQuestionId()));

        Answer answer = answerRepository.save(Answer.createAnswer(answerCreateDto));

        question.updateAnswer(answer);

        return AnswerDto.from(answer);
    }

    @Transactional
    public AnswerDto updateAnswer(AnswerUpdateDto answerUpdateDto) {
        Answer answer = answerRepository.findById(answerUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Answer not found: " + answerUpdateDto.getId()));

        answer.updateAnswer(answerUpdateDto);

        return AnswerDto.from(answer);
    }

    @Transactional
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    @Transactional
    public AnswerDto createEmotion(EmotionCreateDto emotionCreateDto) {
        Answer answer = answerRepository.findById(emotionCreateDto.getAnswerId())
                .orElseThrow(() -> new EntityNotFoundException("Answer not found: " + emotionCreateDto.getAnswerId()));

        Emotion emotion = Emotion.from(emotionCreateDto);

        answer.updateEmotion(emotion);

        return AnswerDto.from(answer);
    }

    @Transactional
    public AnswerDto updateEmotion(EmotionCreateDto emotionCreateDto) {
        Answer answer = answerRepository.findById(emotionCreateDto.getAnswerId())
                .orElseThrow(() -> new EntityNotFoundException("Answer not found: " + emotionCreateDto.getAnswerId()));

        Emotion emotion = answer.getEmotions().stream()
                .filter(e -> e.getAuthor().equals(emotionCreateDto.getAuthor()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Emotion not found: " + emotionCreateDto.getAuthor()));

        Emoji emoji = Emoji.fromString(emotionCreateDto.getEmoji());

        emotion.updateEmoji(emoji);

        return AnswerDto.from(answer);
    }

    @Transactional
    public AnswerDto removeEmotion(EmotionRemoveDto emotionRemoveDto) {
        Answer answer = answerRepository.findById(emotionRemoveDto.getAnswerId())
                .orElseThrow(() -> new EntityNotFoundException("Answer not found: " + emotionRemoveDto.getAnswerId()));

        Emotion emotion = answer.getEmotions().stream()
                .filter(e -> e.getAuthor().equals(emotionRemoveDto.getAuthor()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Emotion not found: " + emotionRemoveDto.getAuthor()));

        answer.removeEmotion(emotion);

        return AnswerDto.from(answer);
    }
}
