package com.mycompany.quizgenerator;

/**
 *
 * @author UZAIR
 */
public class User {
    private String userName;
    private String password;
    private String role;
    private int Score;
    
    public User(){
        userName=null;
        password=null;
        role=null;
        Score=0;
    }
    
    public User(String u, String p, String r, int s){
        userName=u;
        password=p;
        role=r;
        Score=s;
    }
    
    public void setUserName(String str){
        userName=str;
    }
    public void setPassword(String str){
        password=str;
    }
    public void setRole(String str){
        role=str;
    }
    public void setScore(int num){
        Score=num;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }
    public int getScore(){
        return Score;
    }
}
