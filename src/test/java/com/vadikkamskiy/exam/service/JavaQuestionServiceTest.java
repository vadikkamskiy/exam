package com.vadikkamskiy.exam.service;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.repository.QuestionRepository;
import com.vadikkamskiy.exam.service.impl.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;
    private QuestionRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(QuestionRepository.class);
        javaQuestionService = new JavaQuestionService(repository);
        javaQuestionService.addQuestion("What is Java?", "A programming language");
        javaQuestionService.addQuestion("What is Polymorphism?", "OOP concept");
    }

    @Test
    void testAddQuestion() {
        Question question = javaQuestionService.addQuestion("What is an Interface?", "A blueprint of a class");
        assertNotNull(question);
        assertEquals("What is an Interface?", question.getQuestion());
        assertEquals("A blueprint of a class", question.getAnswer());

        Set<Question> allQuestions = javaQuestionService.getAllQuestions();
        assertEquals(3, allQuestions.size());
    }

    @Test
    void testRemoveQuestion() {
        Question question = new Question("What is Java?", "A programming language");
        javaQuestionService.removeQuestion("What is Java?", "A programming language");

        Set<Question> allQuestions = javaQuestionService.getAllQuestions();
        assertEquals(1, allQuestions.size());
        assertFalse(allQuestions.contains(question));
    }

    @Test
    void testRemoveNonExistingQuestionThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            javaQuestionService.removeQuestion("Non-existing question", "Answer");
        });
        assertEquals("Question not found", exception.getMessage());
    }

    @Test
    void testGetRandomQuestion() {
        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(javaQuestionService.getAllQuestions().contains(randomQuestion));
    }

    @Test
    void testGetRandomQuestionThrowsExceptionIfNoQuestions() {
        javaQuestionService.removeQuestion("What is Java?", "A programming language");
        javaQuestionService.removeQuestion("What is Polymorphism?", "OOP concept");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            javaQuestionService.getRandomQuestion();
        });
        assertEquals("No questions available", exception.getMessage());
    }
}
