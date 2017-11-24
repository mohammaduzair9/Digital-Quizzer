package com.quizer.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author UZAIR
 */
@Entity
@DiscriminatorValue("truefalse") 
public class TrueFalse extends Question {

    @Column(name = "optionTrue") 
    private String optionTrue;
    
    @Column(name = "optionFalse") 
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
