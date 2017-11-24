package com.quizer.dao;

import com.quizer.model.Question;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author UZAIR
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Question> getQuestionList() {
        Criteria criteria = getSession().createCriteria(Question.class);
        
        return (List<Question>) criteria.list();
    }

    @Override
    public void saveOrUpdate(Question question) {
        getSession().saveOrUpdate(question);
    }

    @Override
    public void deleteQuestion(int id) {
        Question question = (Question) getSession().get(Question.class,id);
        getSession().delete(question);
    }
    
    @Override
    public Question findQuestionById(int id) {
        return (Question) getSession().get(Question.class, id);
    }

    
}
