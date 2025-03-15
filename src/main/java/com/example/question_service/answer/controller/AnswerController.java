package com.example.question_service.answer.controller;

import com.example.question_service.answer.dto.AnswerCreateDto;
import com.example.question_service.answer.dto.AnswerDto;
import com.example.question_service.answer.dto.AnswerUpdateDto;
import com.example.question_service.answer.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDto> getAnswer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(answerService.getAnswer(id));
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<AnswerDto>> getAnswersByQuestionId(@PathVariable("questionId") Long questionId) {
        return ResponseEntity.ok(answerService.getAnswersByQuestionId(questionId));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<AnswerDto>> getAnswersByAuthor(@PathVariable("author") String author) {
        return ResponseEntity.ok(answerService.getAnswersByAuthor(author));
    }

    @PostMapping
    public ResponseEntity<AnswerDto> createAnswer(@Valid @RequestBody AnswerCreateDto answerCreateDto) {
        return ResponseEntity.ok(answerService.createAnswer(answerCreateDto));
    }

    @PutMapping
    public ResponseEntity<AnswerDto> updateAnswer(@Valid @RequestBody AnswerUpdateDto answerUpdateDto) {
        return ResponseEntity.ok(answerService.updateAnswer(answerUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable("id") Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
