package com.vadikkamskiy.exam.repository.impl;

import com.vadikkamskiy.exam.model.Question;
import com.vadikkamskiy.exam.repository.QuestionRepository;

import com.vadikkamskiy.exam.service.impl.JavaQuestionService;
import com.vadikkamskiy.exam.service.impl.MathQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Slf4j
@Repository
public class InMemoryQuestionRepository implements QuestionRepository {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

//        public InMemoryQuestionRepository(MathQuestionService math, JavaQuestionService java){
//        questions.add((Question) math.getAllQuestions());
//        questions.add((Question) java.getAllQuestions());
//    }
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
