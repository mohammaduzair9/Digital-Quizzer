package com.quizer.dao;

import com.quizer.model.Quiz;
import java.util.List;

public interface QuizDao {

    public List<Quiz> getQuizList();
    
    public void saveOrUpdate(Quiz quiz);
    
    public void deleteQuiz(int id);
    
    public Quiz findQuizById(int id);
    
}
