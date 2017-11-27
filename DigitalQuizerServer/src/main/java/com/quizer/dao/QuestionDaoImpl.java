package com.quizer.dao;

import com.quizer.model.Question;
import com.quizer.model.Quiz;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    public List<Question> getQuestionList(int id) {
        
        Criteria cQuiz = sessionFactory.getCurrentSession().createCriteria(Quiz.class);
        cQuiz.add(Restrictions.like("id", id));
        Quiz quiz = (Quiz) cQuiz.uniqueResult();
        
        Criteria cQuest = sessionFactory.getCurrentSession().createCriteria(Question.class);
        cQuest.add(Restrictions.like("quiz", quiz));
        
        return (List<Question>) cQuest.list();
    
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
