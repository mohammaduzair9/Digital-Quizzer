/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myquizer;

import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author UZAIR
 */
public class MainControllerTest {
    
    public MainControllerTest() {
    }
    
    
    @Test
    public void testTryLogin() {
        System.out.println("tryLogin");
        String username = "user";
        String password = "pass";
        user user1 = new user("uzair","1234","Student",0);
        user user2 = new user("uzair2","1234","instructor",0); 
        MainController instance = new MainController();
        instance.user1=user1;
        instance.user2=user2;
        user result = instance.tryLogin(username, password);
        user expRes = null;
        assertEquals(expRes, result);
        
    }
    
}
