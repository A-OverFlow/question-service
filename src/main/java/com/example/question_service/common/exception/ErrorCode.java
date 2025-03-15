package com.example.question_service.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //bad request
    QUESTION_INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "QUESTION-4000", "입력값이 잘못되었습니다."),
    //unauthorized
    QUESTION_AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "QUESTION-4010", "인증에 실패하였습니다."),
    //internal server error
    QUESTION_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "QUESTION-5000", "서버에 오류가 발생하였습니다."),
    //not found
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "QUESTION-4041", "질문을 찾을 수 없습니다."),
    //conflict
    QUESTION_DUPLICATE(HttpStatus.CONFLICT, "QUESTION-4090", "질문이 이미 존재합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
