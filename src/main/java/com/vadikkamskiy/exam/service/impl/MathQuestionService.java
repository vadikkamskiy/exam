package com.vadikkamskiy.exam.service.impl;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.repository.QuestionRepository;
import com.vadikkamskiy.exam.service.QuestionService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Service("MathQuestionService")
@Qualifier("MathQuestionService")
public class MathQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;

    public MathQuestionService(@Qualifier("MathQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    @PostConstruct
    public void init() {
        addQuestion("2 + 2", "4");
        addQuestion("5 * 3", "15");
        addQuestion("10 - 3", "7");
        addQuestion("9 / 3", "3");
    }

    @Override
    public Question addQuestion(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questionRepository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question removeQuestion(String question, String answer) {
        Question q = new Question(question, answer);
        return questionRepository.remove(q);
    }

    @Override
    public Set<Question> getAllQuestions() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (questionRepository.getAll().isEmpty()) {
            log.warn("No questions available");
            throw new RuntimeException("No questions available");
        }
        Random random = new Random();
        int index = random.nextInt(questionRepository.getAll().size());
        Question result = new ArrayList<>(questionRepository.getAll()).get(index);
        log.info("Returning random question: {}", result.getQuestion());
        return result;
    }
}
