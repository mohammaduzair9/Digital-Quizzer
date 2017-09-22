/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myquizer;

import java.util.ArrayList;
import java.util.List;
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
public class QuizTest {
    
    public QuizTest() {
    }
    
    @Test
    public void testAddQues() {
        System.out.println("addQues");
        Question quest = new Question();
        quest.setQuest("This is a Question");
        Quiz instance = new Quiz();
        instance.addQues(quest);
        String result=instance.getQuestList().get(0).getQuest();
        String expectedResult = "This is a Question";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetQuestList() {
        System.out.println("getQuestList");
        Quiz instance = new Quiz();
        Question q = new Question();
        Question q1 = new Question();
        instance.addQues(q);
        instance.addQues(q1);
        List<Question> expResult = new ArrayList<>();
        expResult.add(q);
        expResult.add(q1);
        List<Question> result = instance.getQuestList();
        assertEquals(expResult, result);
        
    }

    
}
