package com.quizer.service;

import com.quizer.dao.QuestionDao;
import com.quizer.model.Mcq;
import com.quizer.model.Question;
import com.quizer.model.TrueFalse;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author UZAIR
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{

    QuestionDao questionDao;
    
    @Autowired
    public void setQuestionDao(QuestionDao questionDao){
        this.questionDao=questionDao;
    }
    
    @Override
    public List<Question> getQuestionList(int id) {
        return questionDao.getQuestionList(id);
    }

    @Override
    public void saveList(List<Question> questions){
        questionDao.saveList(questions);
    }
    
    @Override
    public void saveOrUpdate(Question question) {
        questionDao.saveOrUpdate(question);
    }

    @Override
    public void saveMcq(Mcq question) {
        questionDao.saveMcq(question);
    }
    
    @Override
    public void saveTrueFalse(TrueFalse question) {
        questionDao.saveTrueFalse(question);
    }
    
    @Override
    public void saveNumeric(Question question) {
        questionDao.saveNumeric(question);
    }
    
    @Override
    public void deleteQuestion(int id) {
        questionDao.deleteQuestion(id);
    }

    @Override
    public Question findQuestionById(int id) {
        return questionDao.findQuestionById(id);
    }

    
}
