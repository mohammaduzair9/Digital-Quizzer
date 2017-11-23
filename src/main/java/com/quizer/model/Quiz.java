package com.quizer.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author UZAIR
 */
@Entity
@Table(name="quiz")
public class Quiz implements Serializable {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "id")
    @OneToMany @JoinColumn(name = "fk_quiz")
    private List<Question> questions;
   
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setTitle(String title){
       this.title = title;
    }
    
    public String getTitle(){
       return title;
    }
    
    public void setDescription(String description){
       this.description = description;
    }
    
    public String getDescription(){
       return description;
    }
    
    public void setQuestions(List<Question> questions ){
       this.questions = questions;
    }

    public List<Question> getQuestions(){
       return questions;
    }
    
}
