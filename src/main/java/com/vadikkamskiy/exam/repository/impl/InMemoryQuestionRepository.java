package com.vadikkamskiy.exam.repository.impl;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.repository.QuestionRepository;

import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service("QuestionRepository")
@Qualifier("QuestionRepository")
public class InMemoryQuestionRepository implements QuestionRepository {

    @Override
    public void addAll(Set<Question> questionsToAdd) {
        questions.addAll(questionsToAdd);
    }

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new RuntimeException("Question not found");
        }
        questions.remove(question);
        log.info("Removed question: {}", question.getQuestion());
        return question;
    }

    @Override
    public Set<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRand(){
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
