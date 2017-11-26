package com.quizer.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author UZAIR
 */
@Entity
@Table(name="QUESTIONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",discriminatorType=DiscriminatorType.STRING)   
@DiscriminatorValue(value="numeric")         
public class Question implements Serializable {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "QUES_ID")
    private int id;
    
    @Column(name = "QUESTION") 
    private String question;
    
    @Column(name = "ANSWER") 
    private String answer;
    
    @Column(name = "MARKS") 
    private int marks;
    
    @ManyToOne    
    @JoinColumn(name = "QUIZ_ID") 
    private Quiz quiz;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }

    
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion(){
        return question;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public void setMarks(int marks) {
        this.marks = marks;
    }
   
    public int getMarks() {
        return marks;
    }
    
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
   
    public Quiz getQuiz() {
        return quiz;
    }
    
}