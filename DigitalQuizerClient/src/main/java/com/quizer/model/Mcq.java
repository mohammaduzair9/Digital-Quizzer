package com.quizer.model;

/**
 *
 * @author UZAIR
 */
public class Mcq extends Question{
    
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    
    public void setOptionA(String optionA) {
        this.optionA=optionA;    
    }
    
    public String getoptionA(){
        return optionA;
    }
 
    public void setOptionB(String optionB) {
        this.optionB=optionB;    
    }
    
    public String getoptionB(){
        return optionB;
    }
    
    public void setOptionC(String optionC) {
        this.optionC=optionC;    
    }
    
    public String getoptionC(){
        return optionC;
    }
    
    public void setOptionD(String optionD) {
        this.optionD=optionD;    
    }
    public String getoptionD(){
        return optionD;
    }

    
}
