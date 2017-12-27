package com.quizer.bo;

import com.quizer.model.User;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author UZAIR
 */
public class UserBo {

    public static final String REST_SERVICE_URI = "http://10.0.2.2:8080/DigitalQuizerServer";
    RestTemplate restTemplate = new RestTemplate();

    /* POST USER OBJECT TO SERVER FOR LOGIN VALIDATION*/
    public User getUser(String username, String password){

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        user = restTemplate.postForObject(REST_SERVICE_URI+"/login/" , user , User.class);

        return user;
    }

}
