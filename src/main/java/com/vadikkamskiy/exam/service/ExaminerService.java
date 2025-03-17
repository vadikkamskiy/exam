package com.vadikkamskiy.exam.service;

import com.vadikkamskiy.exam.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}