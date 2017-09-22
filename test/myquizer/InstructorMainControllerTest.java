/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myquizer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class InstructorMainControllerTest {
    
    public InstructorMainControllerTest() {
         
    }
    
    @Test
    public void testStartMakingQuiz(){
        System.out.println("startMakingQuiz");
        String qTitle="";
        String qDesc="No title";
        InstructorMainController instance = new InstructorMainController();
        boolean result = instance.startMakingQuizAfter(qTitle,qDesc);
        assertFalse(result);
        
    }
    
    @Test
    public void testStartMakingQuiz2(){
        System.out.println("startMakingQuiz");
        String qTitle="This";
        String qDesc="title";
        InstructorMainController instance = new InstructorMainController();
        boolean result = instance.startMakingQuizAfter(qTitle,qDesc);
        assertTrue(result);
    }

    @Test
    public void testMakeQuiz(){
        System.out.println("makeQuiz");
        List<Question> quesList = null;
        Quiz testQuiz=new Quiz();
        testQuiz.setQuestList(quesList);
        InstructorMainController instance = new InstructorMainController();
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
        InstructorMainController instance = new InstructorMainController();
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
        InstructorMainController instance = new InstructorMainController();
        questList.add(q1);questList.add(q2);questList.add(q3);questList.add(q4);
        instance.quesList=questList;
        Question currQues =q2;
        int newIndex=2;
        
        Question result = instance.chngQuesIndexAfter(newIndex,currQues).get(1);
        Question expectedResult = q2;
        assertEquals(expectedResult, result);
    }
    
}
