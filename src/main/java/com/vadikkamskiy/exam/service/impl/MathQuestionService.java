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
@Service("mathQuestionService")
@Qualifier("mathQuestionService")
public class MathQuestionService implements QuestionService {

    private final Set<Question> mathQuestion = new HashSet<>();
    private final Random random = new Random();
    private final QuestionRepository repository;
    public MathQuestionService(QuestionRepository questionRepository) {
        this.repository = questionRepository;
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
        mathQuestion.add(newQuestion);
        repository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question removeQuestion(String question, String answer) {
        log.info("Removing question: {} with answer: {}", question, answer);
        Question q = new Question(question, answer);
        if (!mathQuestion.remove(q)) {
            log.warn("Question not found: {}", question);
            throw new RuntimeException("Question not found");
        }
        mathQuestion.remove(q);
        repository.remove(q);
        return q;
    }

    @Override
    public Set<Question> getAllQuestions() {
        return mathQuestion;
    }

    @Override
    public Question getRandomQuestion() {
        if (mathQuestion.isEmpty()) {
            throw new RuntimeException("No questions available");
        }
        int index = random.nextInt(mathQuestion.size());
        return mathQuestion.stream().toList().get(index);
    }
}
