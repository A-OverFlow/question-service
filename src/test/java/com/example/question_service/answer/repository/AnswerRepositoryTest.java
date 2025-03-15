package com.example.question_service.answer.repository;

import com.example.question_service.answer.entity.Answer;
import com.example.question_service.answer.entity.AnswerStatus;
import com.example.question_service.config.TestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@Import(TestConfig.class)
@ActiveProfiles("test")
class AnswerRepositoryTest {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private EntityManager em;

    //dummy data
    private Answer answer;

    @BeforeEach
    public void init() {
        //init data
        Answer ans = Answer.createAnswer("답변 내용", "답변 작성자", AnswerStatus.NOT_ACCEPTED);
        answer = answerRepository.save(ans);
    }

    @Test
    @DisplayName("답변 조회 테스트")
    public void getAnswerTest() {
        //given
        Long answerId = answer.getId();

        //when
        Answer findAnswer = answerRepository.findById(answerId)
                .orElseThrow(EntityNotFoundException::new);

        //then
        assertEquals(answer.getId(), findAnswer.getId());
    }
}