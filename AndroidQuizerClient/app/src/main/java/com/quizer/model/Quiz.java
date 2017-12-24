package com.quizer.model;

/**
 *
 * @author UZAIR
 */
public class Quiz {

    private int id;
    private String title;
    private String description;
    private List<Question> questions;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setQuestions(List<Question> questions ){
        this.questions = questions;
    }

    public List<Question> getQuestions(){
        return questions;
    }

}
