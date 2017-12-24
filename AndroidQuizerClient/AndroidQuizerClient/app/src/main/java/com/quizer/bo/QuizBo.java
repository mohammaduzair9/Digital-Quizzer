package com.quizer.bo;

import com.quizer.model.Quiz;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author UZAIR
 */
public class QuizBo {


    public static final String REST_SERVICE_URI = "http://10.0.2.2:8080/DigitalQuizerServer";
    RestTemplate restTemplate = new RestTemplate();
    List<Quiz> quizes = new ArrayList<>();

    /* GET LIST OF ALL QUIZES*/
    public List<Quiz> getQuizes(){

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<Quiz[]> quizes = restTemplate.getForEntity( REST_SERVICE_URI+"/quizes/" , Quiz[].class);
        return Arrays.asList(quizes.getBody());
    }

    /* POST A NEW QUIZ TO SERVER*/
    public Quiz addQuiz(String title, String description) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setDescription(description);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Quiz quiz_with_id = restTemplate.postForObject( REST_SERVICE_URI+"/addquiz/" , quiz , Quiz.class);

        quizes.add(quiz_with_id);
        return quiz_with_id;
    }

}
