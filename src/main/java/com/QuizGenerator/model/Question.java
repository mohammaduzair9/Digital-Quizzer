package com.QuizGenerator.model;

import java.io.Serializable;

//Question class
public class Question implements Serializable  {
    
    private String quest;
    private String correctAnswer;
    private int maxMarks;
    
    //constructor
    public Question() {
        quest=null;
        correctAnswer=null;
        maxMarks=0;
    }
    
    //setters
    public void setQuest(String str) {
        quest=str;
    }
    public void setCorrectAnswer(String str) {
        correctAnswer=str;
    }
    public void setMaxMarks(int marks) {
        maxMarks=marks;
    }
   
    //getters
    public String getQuest(){
        return quest;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public int getMaxMarks() {
        return maxMarks;
    }
}