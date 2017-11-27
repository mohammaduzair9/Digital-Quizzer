package com.quizer.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author UZAIR
 */
@Entity
@Table(name="USERS")
public class User implements Serializable {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;
    @Column(name="USERNAME" , unique = true)
    private String username;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="TYPE")
    private String type;
    @Column(name="SCORE")
    private int score;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setUserName(String username){
        this.username = username;
    }
    
    public String getUserName(){
        return username;
    }
    
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getType(){
        return type;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
    
}    
