package com.QuizGenerator.controller;

import com.QuizGenerator.controller.LoginController;
import com.QuizGenerator.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author UZAIR
 */
public class StudentControllerTest {
    
    public StudentControllerTest() {
    }
    
    
    @Test
    public void testTryLogin() {
        System.out.println("tryLogin");
        String username = "user";
        String password = "pass";
        User user1 = new User("uzair","1234","Student",0);
        User user2 = new User("uzair2","1234","instructor",0); 
        LoginController instance = new LoginController();
        instance.user1=user1;
        instance.user2=user2;
        User result = instance.tryLogin(username, password);
        User expRes = null;
        assertEquals(expRes, result);
        
    }
    
}
