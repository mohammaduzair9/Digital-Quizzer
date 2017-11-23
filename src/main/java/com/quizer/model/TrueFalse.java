package com.quizer.model;

/**
 *
 * @author UZAIR
 */
public class TrueFalse extends Question {

    private String optionTrue;
    private String optionFalse;
    
    public void setOptionTrue(String optionTrue) {
        this.optionTrue = optionTrue;
    }
    
    public String getoptionTrue(){
        return optionTrue;
    }

    public void setOptionFalse(String optionFalse) {
        this.optionFalse = optionFalse;
    }
    
    public String getoptFalse(){
        return optionFalse;
    }
    
}
