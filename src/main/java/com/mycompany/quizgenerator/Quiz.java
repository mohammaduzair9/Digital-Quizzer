package com.mycompany.quizgenerator;

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
       questions=new ArrayList <>() ;
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
   
   public void setQuestList(List<Question> updList ){
       this.questions=updList;
   }
   
   

}
