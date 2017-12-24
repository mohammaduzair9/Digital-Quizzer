package com.quizer.bo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizer.model.MCQ;
import com.quizer.model.Question;
import com.quizer.model.Quiz;
import com.quizer.model.TrueFalse;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UZAIR
 */
public class QuestionBo {

    public static final String REST_SERVICE_URI = "http://10.0.2.2:8080/DigitalQuizerServer";
    RestTemplate restTemplate = new RestTemplate();
    List<Question> quesList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    /* GET */
    public List<Question> getQuestions(int id) throws IOException {

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<List<Question>> res;
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI+"/questions/"+id, Object[].class);
        Object[] objects = responseEntity.getBody();

        List<Question> questions = new ArrayList<>();
        for(int i=0; i<objects.length; i++){
            String questionInJson  = mapper.writeValueAsString(objects[i]);
            if(questionInJson.contains("optionA") && questionInJson.contains("optionB") && questionInJson.contains("optionC") && questionInJson.contains("optionD")){
                MCQ ques = mapper.readValue(questionInJson, MCQ.class);
                questions.add(ques);
            }
            else if(questionInJson.contains("optionTrue") && questionInJson.contains("optionFalse")){
                TrueFalse ques = mapper.readValue(questionInJson, TrueFalse.class);
                questions.add(ques);
            }
            else{
                Question ques = mapper.readValue(questionInJson, Question.class);
                questions.add(ques);
            }

        }

        return questions;
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

        MCQ question = new MCQ();
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

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        for(int i=0;i<quesList.size();i++){
            Question question = quesList.get(i);

            if(question instanceof TrueFalse)
                restTemplate.postForObject( REST_SERVICE_URI+"/addtruefalse/" , (TrueFalse)question , TrueFalse.class);

            else if (question instanceof MCQ)
                restTemplate.postForObject( REST_SERVICE_URI+"/addmcq/" , (MCQ)question , MCQ.class);

            else
                restTemplate.postForObject( REST_SERVICE_URI+"/addnumeric/" , question , Question.class);

        }

    }

}

