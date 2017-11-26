package com.quizer.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author UZAIR
 */
@Entity
@DiscriminatorValue("mcq") 
public class Mcq extends Question {
    
    @Column(name = "OPT_A") 
    private String optionA;
    
    @Column(name = "OPT_B")
    private String optionB;
    
    @Column(name = "OPT_C")
    private String optionC;
    
    @Column(name = "OPT_D")
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
