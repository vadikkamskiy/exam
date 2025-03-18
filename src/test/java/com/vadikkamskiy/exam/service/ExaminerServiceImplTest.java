package com.vadikkamskiy.exam.service;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void testGetQuestionsReturnsCorrectAmount() {
        when(questionService.getAllQuestions()).thenReturn(List.of(
                new Question("What is Java?", "A programming language"),
                new Question("What is Polymorphism?", "OOP concept"),
                new Question("What is an Interface?", "A blueprint of a class")
        ));

        Set<Question> questions = (Set<Question>) examinerService.getQuestions(2);
        assertEquals(2, questions.size());
    }

    @Test
    void testGetQuestionsThrowsExceptionIfNotEnoughQuestions() {
        when(questionService.getAllQuestions()).thenReturn(List.of(
                new Question("What is Java?", "A programming language")
        ));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            examinerService.getQuestions(3);
        });

        assertEquals("Requested more questions than available", exception.getMessage());
    }

    @Test
    void testGetQuestionsReturnsUniqueQuestions() {
        when(questionService.getAllQuestions()).thenReturn(List.of(
                new Question("What is Java?", "A programming language"),
                new Question("What is Polymorphism?", "OOP concept"),
                new Question("What is an Interface?", "A blueprint of a class")
        ));

        Set<Question> questions = (Set<Question>) examinerService.getQuestions(3);

        assertEquals(3, questions.size());
    }
}
