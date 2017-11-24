package com.QuizGenerator.model;

import com.QuizGenerator.model.Quiz;
import com.QuizGenerator.model.Question;
import java.util.ArrayList;
import java.util.List;
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
