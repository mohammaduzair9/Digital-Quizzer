package com.quizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author UZAIR
 */
@Entity
@Table(name="QUIZES")
public class Quiz implements Serializable {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "QUIZ_ID")
    private int id;
    
    @Column(name = "TITLE")
    private String title;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
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
