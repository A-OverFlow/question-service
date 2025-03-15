package com.example.question_service.question.repository;

import com.example.question_service.question.entity.Question;

import java.util.List;

public interface QuestionCustomRepository {

    List<Question> findQuestionByAnswerAuthor(String author);

    List<Question> findQuestionByHashTagName(String name);
}
