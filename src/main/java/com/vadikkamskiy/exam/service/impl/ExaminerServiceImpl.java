package com.vadikkamskiy.exam.service.impl;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.repository.QuestionRepository;
import com.vadikkamskiy.exam.service.ExaminerService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionRepository allQuestion;
    private final Random rand = new Random();

    public ExaminerServiceImpl(
            QuestionRepository allQuestion) {
        this.allQuestion = allQuestion;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        Set<Question> result = new HashSet<>();
        List<Question> allQuestions = new ArrayList<>(allQuestion.getAll());
        System.out.println(allQuestion.getAll());
        if (amount > allQuestions.size()) {
            throw new IllegalArgumentException("Requested more questions than available");
        }

        Random rand = new Random();
        while (result.size() < amount) {
            Question question = allQuestions.get(rand.nextInt(allQuestions.size()));
            result.add(question);
        }

        return result;
    }
}
