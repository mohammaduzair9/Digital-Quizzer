package com.quizer.Bo;

import com.quizer.model.Question;
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
    
    /* GET */
    public List<Question> getQuestions(int id){
        
        ResponseEntity<Question[]> questions = restTemplate.getForEntity( REST_SERVICE_URI+"/questions/"+id , Question[].class);
        return Arrays.asList(questions.getBody());
    }
    
}
