package com.quizer.service;

import com.quizer.dao.QuestionDao;
import com.quizer.model.Question;
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
    public List<Question> getQuestionList() {
        return questionDao.getQuestionList();
    }

    @Override
    public void saveOrUpdate(Question question) {
        questionDao.saveOrUpdate(question);
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
