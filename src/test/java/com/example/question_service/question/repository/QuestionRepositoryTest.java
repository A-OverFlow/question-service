package com.example.question_service.question.repository;

import com.example.question_service.answer.entity.Answer;
import com.example.question_service.answer.entity.AnswerStatus;
import com.example.question_service.config.TestConfig;
import com.example.question_service.question.dto.QuestionUpdateDto;
import com.example.question_service.question.dto.QuestionUpdateKey;
import com.example.question_service.question.entity.*;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@Import(TestConfig.class)
@ActiveProfiles("test")
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private HashTagRepository hashTagRepository;

    @Autowired
    private EntityManager em;

    //dummy data
    private Question question;

    private final static String TEST_QUESTION_SUBJECT = "질문 제목";
    private final static String TEST_QUESTION_CONTENT = "질문 내용";
    private final static String TEST_QUESTION_WRITER = "질문 작성자";
    private final static QuestionStatus TEST_QUESTION_QUESTION_STATUS = QuestionStatus.ING;

    private final static String TEST_ANSWER_CONTENT = "답변 내용";
    private final static String TEST_ANSWER_AUTHOR = "답변 작성자";
    private final static AnswerStatus TEST_ANSWER_ANSWER_STATUS = AnswerStatus.NOT_ACCEPTED;

    private final static String TEST_COMMENT_CONTENT = "댓글 내용";
    private final static String TEST_COMMENT_AUTHOR = "댓글 작성자";

    @BeforeEach
    public void init() {

        //init data
        Question quest = Question.of(
                TEST_QUESTION_SUBJECT,
                TEST_QUESTION_CONTENT,
                TEST_QUESTION_WRITER,
                TEST_QUESTION_QUESTION_STATUS
        );

        Answer answer = Answer.createAnswer(
                TEST_ANSWER_CONTENT,
                TEST_ANSWER_AUTHOR,
                TEST_ANSWER_ANSWER_STATUS
        );

        Comment comment = Comment.createComment(
                TEST_COMMENT_CONTENT,
                TEST_COMMENT_AUTHOR
        );

        quest.updateAnswer(answer);
        answer.updateComment(comment);

        question = questionRepository.save(quest);
    }

    @Test
    @DisplayName("질문 등록 테스트")
    public void createQuestionTest() {

        //given
        Question quest = Question.of("질문 제목 입니다.", "질문은 무엇인가요?", "작성자", QuestionStatus.ING);

        //when
        Question save = questionRepository.save(quest);

        //then
        em.flush();
        em.clear();

        assertEquals(quest.getSubject(), save.getSubject());
    }

    @Test
    @DisplayName("질문 제목 수정 테스트")
    public void updateQuestionSubjectTest() {

        //given
        Question quest = questionRepository.save(Question.of("질문 제목 입니다.", "질문은 무엇인가요?", "작성자", QuestionStatus.ING));
        String subject = quest.getSubject();

        //when
        String string = QuestionUpdateKey.SUBJECT.toString();

        log.info("string : {}", string);

        quest.updateQuestion(QuestionUpdateDto.from(QuestionUpdateKey.SUBJECT.getKey() ,"질문 제목"));
        questionRepository.save(quest);

        //then
        em.flush();
        em.clear();

        Question findQuestion = questionRepository.findById(quest.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertNotEquals(subject, findQuestion.getSubject());
    }

    @Test
    @DisplayName("질문 내용 수정 테스트")
    public void updateQuestionContentTest() {

        //given
        Question quest = questionRepository.save(Question.of("질문 제목 입니다.", "질문은 무엇인가요?", "작성자", QuestionStatus.ING));
        String content = quest.getContent();

        //when
        quest.updateQuestion(QuestionUpdateDto.from(QuestionUpdateKey.CONTENT.getKey(), "질문 내용"));
        questionRepository.save(quest);

        //then
        em.flush();
        em.clear();

        Question findQuestion = questionRepository.findById(quest.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertNotEquals(content, findQuestion.getContent());
    }

    @Test
    @DisplayName("질문 상태 수정 테스트")
    public void updateQuestionStatusTest() {

        //given
        Question quest = questionRepository.save(Question.of("질문 제목 입니다.", "질문은 무엇인가요?", "작성자", QuestionStatus.ING));
        QuestionStatus status = quest.getStatus();

        //when
        quest.updateQuestion(QuestionUpdateDto.from(QuestionUpdateKey.STATUS.getKey(), QuestionStatus.DONE.toString()));
        questionRepository.save(quest);

        //then
        em.flush();
        em.clear();

        Question findQuestion = questionRepository.findById(quest.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertNotEquals(status, findQuestion.getStatus());
    }

    @Test
    @DisplayName("답변 등록 테스트")
    public void createAnswerTest() {

        //given

        //when

        //then
        em.flush();
        em.clear();

        Optional<Answer> first = question.getAnswers().stream()
                .filter(a -> a.getContent().equals(TEST_ANSWER_CONTENT))
                .findFirst();

        assertTrue(first.isPresent());
    }

    @Test
    @DisplayName("다수 질문 추가 테스트")
    public void createManyAnswerTest() {

        //given
        Question quest = Question.of("질문 제목 입니다.", "질문은 무엇인가요?", "작성자", QuestionStatus.ING);

        //when
        for (int i = 0; i < 10; i++) {
            Answer answer = Answer.createAnswer("답변입니다." + i, "작성자" + i, AnswerStatus.NOT_ACCEPTED);
            quest.updateAnswer(answer);
        }

        //when
        questionRepository.save(quest);

        //then
        em.flush();
        em.clear();

        //test N+1
        quest.getAnswers().forEach(answer -> log.info("answer : {}", answer.getContent()));

        assertEquals(10, quest.getAnswers().size());
    }

    @Test
    @DisplayName("다수 댓글 추가 테스트")
    public void createManyCommentTest() {

        //given
        Question quest = Question.of("질문 제목 입니다.", "질문은 무엇인가요?", "작성자", QuestionStatus.ING);
        Answer answer = Answer.createAnswer("답변입니다.", "작성자", AnswerStatus.NOT_ACCEPTED);
        quest.updateAnswer(answer);

        //when
        for (int i = 0; i < 10; i++) {
            Comment comment = Comment.createComment("댓글입니다." + i, "작성자" + i);
            answer.updateComment(comment);
        }

        questionRepository.save(quest);

        //then
        assertEquals(10, quest.getAnswers().getFirst().getComments().size());
    }

    @Test
    @DisplayName("질문 수정 테스트")
    public void updateAnswerTest() {

        //given
        Answer answer = question.getAnswers().getFirst();

        //when
        answer.updateStatus(AnswerStatus.ACCEPTED);
        Optional<Question> findQuestion = questionRepository.findById(question.getId());

        //then
        assertTrue(findQuestion.isPresent());
        assertEquals(AnswerStatus.ACCEPTED, findQuestion.get().getAnswers().getFirst().getStatus());
    }

    @Test
    @DisplayName("답변 작성자의 질문 목록 조회 테스트")
    public void getQuestionByAnswerAuthorTest() {

        //given
        for (int i = 0; i < 10; i++) {
            Question question = Question.of("질문 제목" + i, "질문 내용" + i, "질문 작성자" + i, QuestionStatus.ING);

            for (int j=0;j<10;j++) {
                Answer answer = Answer.createAnswer("답변입니다." + j, "답변 작성자" + j, AnswerStatus.NOT_ACCEPTED);
                question.updateAnswer(answer);
            }

            questionRepository.save(question);
        }

        //when

        //then
        em.flush();
        em.clear();

        List<Question> questions = questionRepository.findQuestionByAnswerAuthor("답변 작성자1");

        assertEquals(10, questions.size());
    }

    @Test
    @DisplayName("해시 태그 이름으로 질문 찾기")
    public void getQuestionByHashTagName() {

        //given
        for (int i = 0; i < 10; i++) {
            Question question = Question.of("질문 제목" + i, "질문 내용" + i, "작성자" + i, QuestionStatus.ING);
            HashTag hashTag = HashTag.createHashTag("코딩 질문" + i);
            QuestionHashTag questionHashTag = QuestionHashTag.createQuestionHashTag(question, hashTag);

            question.updateQuestionHashTag(questionHashTag);
            hashTag.updateQuestionHashTag(questionHashTag);

            questionRepository.save(question);
            hashTagRepository.save(hashTag);
        }

        //when

        //then
        em.flush();
        em.clear();

        List<Question> questions = questionRepository.findQuestionByHashTagName("코딩 질문1");
        assertFalse(questions.isEmpty());
    }
}
