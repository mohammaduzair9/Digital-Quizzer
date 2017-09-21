/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myquizer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UZAIR
 */
public class Quiz implements Serializable {
   private String title;
   private String desc;
   private List<Question> questions;
   
   public Quiz(){
       title=null;
       desc=null;
       questions=new ArrayList <Question>() ;
   }
   
   public void setTitle(String str){
       title = str;
   }
   
   public void setDesc(String str){
       desc = str;
   }
   
   public void addQues(Question quest){
       questions.add(quest);
   }
   
   public String getTitle(){
       return title;
   }
   
   public String getDesc(){
       return desc;
   }
   
   public List<Question> getQuestList(){
       return questions;
   }
   
   

}
