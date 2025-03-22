package com.vadikkamskiy.exam.service.impl;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.repository.QuestionRepository;
import com.vadikkamskiy.exam.service.ExaminerService;
import com.vadikkamskiy.exam.service.QuestionService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;
    private final QuestionRepository questionRepository;
    private final Set<Question> allQuestions = new HashSet<>();

    public ExaminerServiceImpl(
            @Qualifier("QuestionRepository")QuestionRepository questionRepository,
            @Qualifier("JavaQuestionService")JavaQuestionService javaQuestionService,
            @Qualifier("MathQuestionService")MathQuestionService mathQuestionService) {
                this.javaQuestionService = javaQuestionService;
                this.mathQuestionService = mathQuestionService;
                this.questionRepository = questionRepository;

    }

    @PostConstruct
    private void init() {
        allQuestions.addAll(javaQuestionService.getAllQuestions());
        allQuestions.addAll(mathQuestionService.getAllQuestions());
        questionRepository.addAll(allQuestions);
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        Set<Question> result = new HashSet<>();
        if (amount > allQuestions.size()) {
            throw new IllegalArgumentException("Requested more questions than available");
        }
        while (result.size() < amount) {
            result.add(questionRepository.getRand());
        }
        return result;
    }
    @Override
    public Set<Question> getAllQuestions() {
        Set<Question> result =new HashSet<>();
        result.addAll(mathQuestionService.getAllQuestions());
        result.addAll(javaQuestionService.getAllQuestions());
        return result;
    }
    @Override
    public Collection<Question> getJavaQuestions(){
        return javaQuestionService.getAllQuestions();
    }
    @Override
    public Collection<Question> getMathQuestions(){
        return mathQuestionService.getAllQuestions();
    }
}
