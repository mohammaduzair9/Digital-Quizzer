package com.QuizGenerator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Quiz Class
public class Quiz implements Serializable {
    
    private int _id;
    private String title;
    private String desc;
    private List<Question> questions;
   
    //constructor
    public Quiz(){
       title=null;
       desc=null;
       questions=new ArrayList <>() ;
    }
   
    //setters
    public void setTitle(String str){
       title = str;
    }
    public void setDesc(String str){
       desc = str;
    }
    public void addQues(Question quest){
       questions.add(quest);
    }
    public void setQuestList(List<Question> updList ){
       this.questions=updList;
    }
   
    //getters
    public String getTitle(){
       return title;
    }
    public String getDesc(){
       return desc;
    }
    public List<Question> getQuestList(){
       return questions;
    }
   
}
