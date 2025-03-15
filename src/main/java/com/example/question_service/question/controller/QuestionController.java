package com.example.question_service.question.controller;

import com.example.question_service.question.dto.QuestionCreateDto;
import com.example.question_service.question.dto.QuestionDto;
import com.example.question_service.question.dto.QuestionUpdateDto;
import com.example.question_service.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable("id") Long id) {
        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionCreateDto questionCreateDto) {
        return ResponseEntity.ok(questionService.createQuestion(questionCreateDto));
    }

    @PutMapping
    public ResponseEntity<QuestionDto> updateQuestion(@Valid @RequestBody QuestionUpdateDto questionUpdateDto) {
        return ResponseEntity.ok(questionService.updateQuestion(questionUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
