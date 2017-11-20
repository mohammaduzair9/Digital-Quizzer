package com.QuizGenerator.model;

//TrueFalse Class
public class truefalseQuest extends Question {
    private String optT;
    private String optF;
    
    //setters
    public void setOptions(String opt1, String opt2) {
        optT=opt1;
        optF=opt2;
    }
    
    //getters
    public String getoptT(){
        return optT;
    }
    public String getoptF(){
        return optF;
    }

}
