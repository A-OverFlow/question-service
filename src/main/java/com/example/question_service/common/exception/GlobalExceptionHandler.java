package com.example.question_service.common.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseEntity> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .getFirst()
                .getDefaultMessage();

        return ErrorResponseEntity.toResponseEntity(ErrorCode.QUESTION_INVALID_INPUT_VALUE, errorMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseEntity> handleEntityNotFoundException(EntityNotFoundException e) {
        return ErrorResponseEntity.toResponseEntity(ErrorCode.QUESTION_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseEntity> handleIllegalArgumentException(IllegalArgumentException e) {
        return ErrorResponseEntity.toResponseEntity(ErrorCode.QUESTION_INVALID_INPUT_VALUE, e.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e){
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }
}
