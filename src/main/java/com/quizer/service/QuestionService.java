package com.quizer.service;

import com.quizer.dao.*;
import com.quizer.model.Question;
import java.util.List;

public interface QuestionService {

    public List<Question> getQuestionList();
    
    public void saveOrUpdate(Question question);
    
    public void deleteQuestion(int id);
    
    public Question findQuestionById(int id);
    
}
