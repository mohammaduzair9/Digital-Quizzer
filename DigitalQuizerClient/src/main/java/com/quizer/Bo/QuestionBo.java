package com.quizer.Bo;

import com.quizer.model.Mcq;
import com.quizer.model.Question;
import com.quizer.model.Quiz;
import com.quizer.model.TrueFalse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author UZAIR
 */
public class QuestionBo {
    
    public static final String REST_SERVICE_URI = "http://localhost:8080/DigitalQuizerServer"; 
    RestTemplate restTemplate = new RestTemplate();
    List<Question> quesList = new ArrayList<>();
    
    /* GET */
    public List<Question> getQuestions(int id){
        
        ResponseEntity<Question[]> questions = restTemplate.getForEntity( REST_SERVICE_URI+"/questions/"+id , Question[].class);
        return Arrays.asList(questions.getBody());
    }
    
    public void addNumeric(String quest, String answer, int marks, Quiz quiz) {
        
        Question question = new Question();
        question.setQuestion(quest);
        question.setAnswer(answer);
        question.setMarks(marks);
        question.setQuiz(quiz);
        
        quesList.add(question);
    }
    
    public void addTrueFalse(String quest, String answer, int marks, Quiz quiz, String optTrue, String optFalse) {
        
        TrueFalse question = new TrueFalse();
        question.setQuestion(quest);
        question.setAnswer(answer);
        question.setMarks(marks);
        question.setQuiz(quiz);
        question.setOptionFalse(optFalse);
        question.setOptionTrue(optTrue);
        
        quesList.add(question);
    }
    
    public void addMCQ(String quest, String answer, int marks, Quiz quiz, String optA, String optB, String optC, String optD) {
        
        Mcq question = new Mcq();
        question.setQuestion(quest);
        question.setAnswer(answer);
        question.setMarks(marks);
        question.setQuiz(quiz);
        question.setOptionA(optA);
        question.setOptionB(optB);
        question.setOptionC(optC);
        question.setOptionD(optD);
        
        quesList.add(question);
    }
    
    public void saveQuestions() {
         
        quesList.forEach((Question question) -> {
            System.out.println(question.getQuiz().getId());
            
            if(question instanceof TrueFalse)
                restTemplate.postForObject( REST_SERVICE_URI+"/addtruefalse/" , (TrueFalse)question , TrueFalse.class);
             
            else if (question instanceof Mcq)
                restTemplate.postForObject( REST_SERVICE_URI+"/addmcq/" , (Mcq)question , Mcq.class);
           
            else 
                restTemplate.postForObject( REST_SERVICE_URI+"/addnumeric/" , question , Question.class);
                
            
        });
        
        //ResponseEntity<Question[]> questions = restTemplate.postForEntity( REST_SERVICE_URI+"/addquestions/" , quesList , Question[].class);
        //return Arrays.asList(questions.getBody());
        
    }
    
}
