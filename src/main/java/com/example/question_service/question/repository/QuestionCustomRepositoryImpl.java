package com.example.question_service.question.repository;

import com.example.question_service.question.entity.Question;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.question_service.answer.entity.QAnswer.answer;
import static com.example.question_service.question.entity.QHashTag.*;
import static com.example.question_service.question.entity.QQuestion.*;
import static com.example.question_service.question.entity.QQuestionHashTag.*;

@RequiredArgsConstructor
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Question> findQuestionByAnswerAuthor(String author) {

        return queryFactory
                .selectFrom(question)
                .leftJoin(answer)
                .fetchJoin()
                .on(question.eq(answer.question))
                .where(answer.author.eq(author))
                .fetch();
    }

    @Override
    public List<Question> findQuestionByHashTagName(String name) {

        return queryFactory
                .selectFrom(question)
                .leftJoin(questionHashTag)
                .fetchJoin()
                .on(question.eq(questionHashTag.question))
                .leftJoin(hashTag)
                .fetchJoin()
                .on(questionHashTag.hashTag.eq(hashTag))
                .where(hashTag.name.eq(name))
                .fetch();
    }
}
