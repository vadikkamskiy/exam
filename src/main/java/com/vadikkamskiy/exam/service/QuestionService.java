package com.vadikkamskiy.exam.service;

import com.vadikkamskiy.exam.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question addQuestion(String question, String answer);
    Question removeQuestion(String question, String answer);
    Collection<Question> getAllQuestions();
    Question getRandomQuestion();
}
