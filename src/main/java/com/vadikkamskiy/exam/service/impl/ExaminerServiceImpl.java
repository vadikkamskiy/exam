package com.vadikkamskiy.exam.service.impl;


import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.service.ExaminerService;
import com.vadikkamskiy.exam.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> allQuestions = new ArrayList<>(questionService.getAllQuestions());

        if (amount > allQuestions.size()) {
            throw new IllegalArgumentException("Requested more questions than available");
        }

        Set<Question> selectedQuestions = new HashSet<>();
        while (selectedQuestions.size() < amount) {
            int index = random.nextInt(allQuestions.size());
            selectedQuestions.add(allQuestions.get(index));
        }

        return selectedQuestions;
    }
}
