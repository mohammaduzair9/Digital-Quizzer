package com.quizer.model;

/**
 *
 * @author UZAIR
 */
public class MCQ extends Question {

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    public void setOptionA(String optionA) {
        this.optionA=optionA;
    }

    public String getOptionA(){
        return optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB=optionB;
    }

    public String getOptionB(){
        return optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC=optionC;
    }

    public String getOptionC(){
        return optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD=optionD;
    }
    public String getOptionD(){
        return optionD;
    }

}
