/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myquizer;

/**
 *
 * @author UZAIR
 */
public class user {
    private String userName;
    private String password;
    private String role;
    private int Score;
    
    public user(){
        userName=null;
        password=null;
        role=null;
        Score=0;
    }
    
    public user(String u, String p, String r, int s){
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
