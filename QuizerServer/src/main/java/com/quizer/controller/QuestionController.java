package com.quizer.controller;

import com.quizer.model.Mcq;
import com.quizer.model.Question;
import com.quizer.model.TrueFalse;
import com.quizer.service.QuestionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author UZAIR
 */
@RestController
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    //question list for a quiz mapping
    @RequestMapping(value="/questions/{id}" , method=RequestMethod.GET , headers="Accept=application/json")
    public @ResponseBody List<Question> getQuestionList(@PathVariable("id") int id){
        List<Question> questions = questionService.getQuestionList(id);
        
        return questions;
    }
    
    //add an mcq mapping
    @RequestMapping(value="/addmcq/" , method=RequestMethod.POST)
    public @ResponseBody Mcq add(@RequestBody Mcq question){
        questionService.saveMcq(question);
        
        return question;
    }
    
    //add truefalse mapping
    @RequestMapping(value="/addtruefalse/" , method=RequestMethod.POST)
    public @ResponseBody TrueFalse add(@RequestBody TrueFalse question){
        questionService.saveTrueFalse(question);
        
        return question;
    }
    
    //add numeric mapping
    @RequestMapping(value="/addnumeric/" , method=RequestMethod.POST)
    public @ResponseBody Question add(@RequestBody Question question){
        questionService.saveNumeric(question);
        
        return question;
    }

    //add questions mapping
    @RequestMapping(value="/addquestions/" , method=RequestMethod.POST)
    public @ResponseBody List<Question> add(@RequestBody List<Question> quesList){
        questionService.saveList(quesList);
        
        return quesList;
    }
    
    //update a question mapping
    @RequestMapping(value="/updatequestion/{id}" , method=RequestMethod.PUT)
    public @ResponseBody Question update(@PathVariable("id") int id, @RequestBody Question question ){
        question.setId(id);
        questionService.saveOrUpdate(question);
        
        return question;
    }
    
    //delete question mapping
    @RequestMapping(value="/deletequestion/{id}" , method=RequestMethod.DELETE)
    public @ResponseBody Question delete(@PathVariable("id") int id){ 
        Question question = questionService.findQuestionById(id);
        questionService.deleteQuestion(id);
        
        return question;
    }

    
}
