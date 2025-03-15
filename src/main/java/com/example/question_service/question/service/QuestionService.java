package com.example.question_service.question.service;

import com.example.question_service.question.dto.QuestionCreateDto;
import com.example.question_service.question.dto.QuestionDto;
import com.example.question_service.question.dto.QuestionUpdateDto;
import com.example.question_service.question.entity.Question;
import com.example.question_service.question.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionDto getQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found: " + id));

        return QuestionDto.from(question);
    }

    @Transactional
    public QuestionDto createQuestion(QuestionCreateDto questionCreateDto) {
        Question saveQuestion = questionRepository.save(Question.of(questionCreateDto));
        return QuestionDto.from(saveQuestion);
    }

    @Transactional
    public QuestionDto updateQuestion(QuestionUpdateDto questionUpdateDto) {
        Question question = questionRepository.findById(questionUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found: " + questionUpdateDto.getId()));

        question.updateQuestion(questionUpdateDto);

        return QuestionDto.from(question);
    }

    @Transactional
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
