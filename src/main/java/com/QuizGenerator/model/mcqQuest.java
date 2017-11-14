package com.QuizGenerator.model;

public class mcqQuest extends Question {
    private String optA;
    private String optB;
    private String optC;
    private String optD;
    
    public void setOptions(String opt1, String opt2, String opt3, String opt4) {
        optA=opt1;
        optB=opt2;
        optC=opt3;
        optD=opt4;
    }
    
    public String getoptA(){
        return optA;
    }
    
    public String getoptB(){
        return optB;
    }
    
    public String getoptC(){
        return optC;
    }
    
    public String getoptD(){
        return optD;
    }
    

}

