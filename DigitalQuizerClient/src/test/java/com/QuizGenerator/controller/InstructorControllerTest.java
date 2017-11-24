package com.QuizGenerator.controller;

import com.QuizGenerator.controller.InstructorController;
import com.QuizGenerator.controller.LoginController;
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
public class InstructorControllerTest {
    
    public InstructorControllerTest() {
         
    }
    
    @Test
    public void testStartMakingQuiz(){
        System.out.println("startMakingQuiz");
        String qTitle="";
        String qDesc="No title";
        InstructorController instance = new InstructorController();
        boolean result = instance.startMakingQuizAfter(qTitle,qDesc);
        assertFalse(result);
        
    }
    
    @Test
    public void testStartMakingQuiz2(){
        System.out.println("startMakingQuiz");
        String qTitle="This";
        String qDesc="title";
        InstructorController instance = new InstructorController();
        boolean result = instance.startMakingQuizAfter(qTitle,qDesc);
        assertTrue(result);
    }

    @Test
    public void testMakeQuiz(){
        System.out.println("makeQuiz");
        List<Question> quesList = null;
        Quiz testQuiz=new Quiz();
        testQuiz.setQuestList(quesList);
        InstructorController instance = new InstructorController();
        assertFalse(instance.makeQuizAfter());
        
    }

    @Test
    public void testSubMCQAfter() {
        System.out.println("subMCQ");
        String qMaarks="3";
        String qQues="Check";
        String mcqA="a";
        String mcqB="a";
        String mcqC="a";
        String mcqD="a";        
        InstructorController instance = new InstructorController();
        assertTrue(instance.subMCQAfter(qMaarks,qQues,mcqA,mcqB,mcqC,mcqD));
        
    }

    @Test
    public void testChngQuesIndexAfter() {
        System.out.println("chngQuesIndex");
        Question q1 = new Question();
        Question q2 = new Question();
        Question q3 = new Question();
        Question q4 = new Question();
        List<Question> questList = new ArrayList<>();
        InstructorController instance = new InstructorController();
        questList.add(q1);questList.add(q2);questList.add(q3);questList.add(q4);
        instance.quesList=questList;
        Question currQues =q2;
        int newIndex=4;
        
        Question result = instance.chngQuesIndexAfter(newIndex,currQues).get(1);
        Question expectedResult = q2;
        assertEquals(expectedResult, result);
    }
    
}
