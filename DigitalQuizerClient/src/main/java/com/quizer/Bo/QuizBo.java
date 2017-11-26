package com.quizer.Bo;

import com.quizer.model.Quiz;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author UZAIR
 */
public class QuizBo {
    
    public static final String REST_SERVICE_URI = "http://localhost:8080/DigitalQuizerServer"; 
    RestTemplate restTemplate = new RestTemplate();
    
    /* GET */
    public List<Quiz> getQuizes(){
        
        ResponseEntity<Quiz[]> quizes = restTemplate.getForEntity( REST_SERVICE_URI+"/quizes/" , Quiz[].class);
        return Arrays.asList(quizes.getBody());
    }
    
}
