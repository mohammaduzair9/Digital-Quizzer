package com.quizer.bo;

/**
 *
 * @author UZAIR
 */
public class UserBo {

    public static final String REST_SERVICE_URI = "http://localhost:8080/DigitalQuizerServer";
    RestTemplate restTemplate = new RestTemplate();

    /* POST */
    public User getUser(String username, String password){

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        user = restTemplate.postForObject(REST_SERVICE_URI+"/login/" , user , User.class);

        return user;
    }

}
