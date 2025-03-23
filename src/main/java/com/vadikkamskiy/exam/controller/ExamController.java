package com.vadikkamskiy.exam.controller;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.service.ExaminerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }


    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return examinerService.getAllQuestions();
    }
    
}
