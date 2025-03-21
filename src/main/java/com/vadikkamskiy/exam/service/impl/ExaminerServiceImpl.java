package com.vadikkamskiy.exam.service.impl;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.service.ExaminerService;
import com.vadikkamskiy.exam.service.QuestionService;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;
    Random rand = new Random();

    public ExaminerServiceImpl(
            JavaQuestionService javaQuestionService,
            MathQuestionService mathQuestionService) {
                this.javaQuestionService = javaQuestionService;
                this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        List<Question> allQuestions = new ArrayList<>();
        allQuestions.addAll(mathQuestionService.getAllQuestions());
        allQuestions.addAll(javaQuestionService.getAllQuestions());
        if (amount > allQuestions.size()) {
            throw new IllegalArgumentException("Requested more questions than available");
        }

        Collections.shuffle(allQuestions);

        return new HashSet<>(allQuestions.subList(0, amount));
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
