package com.quizer.dao;

import com.quizer.model.Mcq;
import com.quizer.model.Question;
import com.quizer.model.Quiz;
import com.quizer.model.TrueFalse;
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
        
        List<Question> dcQuest = (List<Question>) cQuest.list();
        
        return (List<Question>) cQuest.list();
    
    }

    @Override
    public void saveList(List<Question> questions){
        
        questions.forEach((Question question) -> {
            if(question instanceof TrueFalse)
                getSession().save((TrueFalse)question);
            
            else if(question instanceof Mcq)
                getSession().save((Mcq)question);
            
            else
                getSession().save(question);
        });
       
    }
    
    @Override
    public void saveOrUpdate(Question question) {
        getSession().saveOrUpdate(question);
    }
    
    @Override
    public void saveMcq(Mcq question) {
        getSession().save(question);
    }
    
    @Override
    public void saveTrueFalse(TrueFalse question) {
        getSession().save(question);
    }
    
    @Override
    public void saveNumeric(Question question) {
        getSession().save(question);
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
