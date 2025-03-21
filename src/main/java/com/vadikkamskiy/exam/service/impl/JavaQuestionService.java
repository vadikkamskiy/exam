package com.vadikkamskiy.exam.service.impl;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.repository.QuestionRepository;
import com.vadikkamskiy.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.PostConstruct;

@Slf4j
@Service("javaQuestionService")
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostConstruct
    private void init() {
        addQuestion("What is Java?", "A programming language");
        addQuestion("What is Polymorphism?", "OOP concept");
        addQuestion("What is an Interface?", "A blueprint of a class");
        addQuestion("Explain Inheritance in Java", "Mechanism of acquiring parent class properties");
        addQuestion("What is the purpose of the JVM?", "To execute Java bytecode");
        log.info("Default questions loaded: {}", questionRepository.getAll().size());
    }

    @Override
    public Question addQuestion(String question, String answer) {
        log.info("Adding question: {} with answer: {}", question, answer);
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
