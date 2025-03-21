package com.vadikkamskiy.exam.config;

import com.vadikkamskiy.exam.repository.impl.InMemoryQuestionRepository;
import com.vadikkamskiy.exam.repository.QuestionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuestionRepositoryConfig {

    @Bean(name = "javaQuestionRepository")
    public QuestionRepository javaQuestionRepository() {
        return new InMemoryQuestionRepository();
    }

    @Bean(name = "mathQuestionRepository")
    public QuestionRepository mathQuestionRepository() {
        return new InMemoryQuestionRepository();
    }
}
