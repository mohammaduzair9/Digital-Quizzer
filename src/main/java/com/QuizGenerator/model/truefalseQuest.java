package com.QuizGenerator.model;

public class truefalseQuest extends Question {
    private String optT;
    private String optF;
    
    public void setOptions(String opt1, String opt2) {
        optT=opt1;
        optF=opt2;
    }
    
    public String getoptT(){
        return optT;
    }
    
    public String getoptF(){
        return optF;
    }

}
