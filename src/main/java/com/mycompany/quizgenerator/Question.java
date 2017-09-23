package com.mycompany.quizgenerator;

import java.io.Serializable;

/**
 *
 * @author UZAIR
 */
class Question implements Serializable  {
    
    private String quest;
    private String correctAnswer;
    private int maxMarks;
    
    public Question() {
        quest=null;
        correctAnswer=null;
        maxMarks=0;
    }
    
    public void setQuest(String str) {
        quest=str;
    }
    
    public void setCorrectAnswer(String str) {
        correctAnswer=str;
    }
    
    public void setMaxMarks(int marks) {
        maxMarks=marks;
    }
    
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

class mcqQuest extends Question {
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

class truefalseQuest extends Question {
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
