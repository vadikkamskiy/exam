package com.vadikkamskiy.exam.service;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.repository.QuestionRepository;
import com.vadikkamskiy.exam.service.impl.MathQuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private QuestionRepository repository;

    @InjectMocks
    private MathQuestionService service;

    @Test
    void testAddQuestion() {
        Question question = new Question("2 + 2", "4");
        when(repository.add(question)).thenReturn(question);

        Question result = service.addQuestion("2 + 2", "4");
        assertEquals(question, result);
    }
}
