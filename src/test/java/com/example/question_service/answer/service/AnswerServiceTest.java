package com.example.question_service.answer.service;

import com.example.question_service.answer.dto.AnswerCreateDto;
import com.example.question_service.answer.dto.AnswerDto;
import com.example.question_service.question.dto.QuestionCreateDto;
import com.example.question_service.question.dto.QuestionDto;
import com.example.question_service.question.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AnswerServiceTest {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @BeforeEach
    public void init() {

        //init data
        QuestionCreateDto questionCreateDto = QuestionCreateDto.from("궁금해요", "이것은 무엇인가요?", "작성자");
        QuestionDto question = questionService.createQuestion(questionCreateDto);

        AnswerCreateDto answerCreateDto = AnswerCreateDto.from(question.getId(), "답변 내용", "작성자");

        answerService.createAnswer(answerCreateDto);
    }

    @Test
    @DisplayName("답변 등록 테스트")
    public void createAnswerTest() {

        //given
        QuestionCreateDto questionCreateDto = QuestionCreateDto.from("질문 제목 입니다.", "질문은 무엇인가요?", "작성자");
        QuestionDto question = questionService.createQuestion(questionCreateDto);

        //when
        AnswerCreateDto answerCreateDto = AnswerCreateDto.from(question.getId(), "답변 내용", "작성자");
        AnswerDto answer = answerService.createAnswer(answerCreateDto);

        //then
        assertDoesNotThrow(() -> answerService.getAnswer(answer.getId()));
    }
}