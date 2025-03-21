package com.vadikkamskiy.exam.service.impl;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.repository.QuestionRepository;
import com.vadikkamskiy.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.PostConstruct;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Slf4j
@Service("javaQuestionService")
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();
    QuestionRepository repository;
    public JavaQuestionService (@Qualifier("inMemoryQuestionRepository") QuestionRepository questionRepository){
        this.repository = questionRepository;
    }

    @PostConstruct
    private void init() {
        addQuestion("What is Java?", "A programming language");
        addQuestion("What is Polymorphism?", "OOP concept");
        addQuestion("What is an Interface?", "A blueprint of a class");
        addQuestion("Explain Inheritance in Java", "Mechanism of acquiring parent class properties");
        addQuestion("What is the purpose of the JVM?", "To execute Java bytecode");
        log.info("Default questions loaded: {}", questions.size());
    }

    @Override
    public Question addQuestion(String question, String answer) {
        log.info("Adding question: {} with answer: {}", question, answer);
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        repository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question removeQuestion(String question, String answer) {
        log.info("Removing question: {} with answer: {}", question, answer);
        Question q = new Question(question, answer);
        if (!questions.remove(q)) {
            log.warn("Question not found: {}", question);
            throw new RuntimeException("Question not found");
        }
        return q;
    }

    @Override
    public Set<Question> getAllQuestions() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            log.warn("No questions available");
            throw new RuntimeException("No questions available");
        }
        int index = random.nextInt(questions.size());
        Question question = new ArrayList<>(questions).get(index);
        log.info("Returning random question: {}", question.getQuestion());
        return question;
    }
}
