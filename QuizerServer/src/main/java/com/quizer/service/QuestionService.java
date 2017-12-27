package com.quizer.service;

import com.quizer.model.Mcq;
import com.quizer.model.Question;
import com.quizer.model.TrueFalse;
import java.util.List;

public interface QuestionService {

    public List<Question> getQuestionList(int id);
    
    public void saveOrUpdate(Question question);
    
    public void saveMcq(Mcq question);

    public void saveTrueFalse(TrueFalse question);
    
    public void saveNumeric(Question question);
      
    public void saveList(List<Question> questions);
    
    public void deleteQuestion(int id);
    
    public Question findQuestionById(int id);
    
}
