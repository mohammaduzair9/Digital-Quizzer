package com.quizer.dao;

import com.quizer.model.Question;
import java.util.List;

public interface QuestionDao {

    public List<Question> getQuestionList();
    
    public void saveOrUpdate(Question question);
    
    public void deleteQuestion(int id);
    
    public Question findQuestionById(int id);
    
}
