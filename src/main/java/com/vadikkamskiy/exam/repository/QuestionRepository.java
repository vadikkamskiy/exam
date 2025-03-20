package com.vadikkamskiy.exam.repository;

import com.vadikkamskiy.exam.model.Question;

import java.util.Set;

public interface QuestionRepository {
    Question add(Question question);
    Question remove(Question question);
    Set<Question> getAll();
    Question getRand();
}
