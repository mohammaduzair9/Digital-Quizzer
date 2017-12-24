package com.quizer.bo;

/**
 *
 * @author UZAIR
 */
public class QuizBo {

    public static final String REST_SERVICE_URI = "http://localhost:8080/DigitalQuizerServer";
    RestTemplate restTemplate = new RestTemplate();
    List<Quiz> quizes = new ArrayList<>();

    /* GET */
    public List<Quiz> getQuizes(){

        ResponseEntity<Quiz[]> quizes = restTemplate.getForEntity( REST_SERVICE_URI+"/quizes/" , Quiz[].class);
        return Arrays.asList(quizes.getBody());
    }

    /* POST */
    public Quiz addQuiz(String title, String description) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setDescription(description);
        Quiz quiz_with_id = restTemplate.postForObject( REST_SERVICE_URI+"/addquiz/" , quiz , Quiz.class);

        quizes.add(quiz_with_id);
        return quiz_with_id;
    }

}
