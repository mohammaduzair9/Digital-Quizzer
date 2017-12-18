package com.quizer.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author UZAIR
 */

//TrueFalse Class
@Entity
@DiscriminatorValue("truefalse") 
public class TrueFalse extends Question {

    @Column(name = "OPT_TRUE") 
    private String optionTrue;
    
    @Column(name = "OPT_FALSE") 
    private String optionFalse;
    
    public void setOptionTrue(String optionTrue) {
        this.optionTrue = optionTrue;
    }
    
    public String getOptionTrue(){
        return optionTrue;
    }

    public void setOptionFalse(String optionFalse) {
        this.optionFalse = optionFalse;
    }
    
    public String getOptionFalse(){
        return optionFalse;
    }
    
}
