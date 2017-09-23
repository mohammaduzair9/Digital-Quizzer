/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quizgenerator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author UZAIR
 */
public class InstructorController implements Initializable {
    @FXML    private Label lblStatus;
    @FXML    private Label lblQuesNum;
    @FXML    private Label lblMarks;
    @FXML    private Label lblErrorMake;
    @FXML    private Label lblErrorDone;    
    @FXML    private Label lblErrorMCQ;
    @FXML    private Label lblErrorTrueFalse;
    @FXML    private Label lblErrorNumeric;
    @FXML    private TextArea txtQues1;
    @FXML    private TextField txtUser;
    @FXML    private TextField txtPass;
    @FXML    private TextField quizTitle;
    @FXML    private TextField txtchngIndex;
    @FXML    private TextArea quizDesc;
    @FXML    private TextArea txtQuesMCQ;
    @FXML    private TextArea txtQuesTrueFalse;
    @FXML    private TextArea txtQuesNumeric;
    @FXML    private TextArea txtAnsNumeric;
    @FXML    private TextArea txtAns;
    @FXML    private TextField txtMCQa;
    @FXML    private TextField txtMCQb;
    @FXML    private TextField txtMCQc;
    @FXML    private TextField txtMCQd;
    @FXML    private TextField txtMarks;
    @FXML    private RadioButton radMCQa;
    @FXML    private RadioButton radMCQb;
    @FXML    private RadioButton radMCQc;
    @FXML    private RadioButton radMCQd;
    @FXML    private RadioButton radTrue;
    @FXML    private RadioButton radFalse;
    @FXML    private RadioButton radMCQa1;
    @FXML    private RadioButton radMCQb1;
    @FXML    private RadioButton radMCQc1;
    @FXML    private RadioButton radMCQd1;
    @FXML    private RadioButton radTrue1;
    @FXML    private RadioButton radFalse1;
    @FXML    private Pane makeQuizPane;
    @FXML    private Pane subQuesPane;
    @FXML    private Pane makeMCQPane;
    @FXML    private Pane makeTrueFalsePane;
    @FXML    private Pane makeNumericPane;
    @FXML    private Pane stdSelectPane;
    @FXML    private Pane editOrMakePane;
    @FXML    private ComboBox cboEdit;
    @FXML    private Button btnMakeNew;
    @FXML    private Button btnNext;
    @FXML    private Button btnEditQuiz;
    @FXML    private Pane insNumericPane;
    @FXML    private Pane insMCQPane;
    @FXML    private Pane insTrueFalsePane;
    @FXML    private Pane editQuizPane;
    
    int QuesNum;
    int index=0;
    List<Question> quesList=new ArrayList<>();
    List<Question> newQuesList=new ArrayList<>();
    Question old;
    Iterator<Question> iter;
    Question currQues;
    private Quiz myQuiz = new Quiz();;
    List<Quiz> quizList = new ArrayList<>();
    String filePath = "Quizes.ser";
    String role="Instructor";
    
    User user1 = new User();
    User user2 = new User();
    User User = new User();
    
    @Override
    public void initialize(URL location,ResourceBundle resources){
        cboEdit.getItems().clear();
        quizFileStream qf = new quizFileStream();
        quizList = qf.readFromFile(filePath);
        for (Quiz quiz : quizList) {
            cboEdit.getItems().add(quiz.getTitle());
	}
    }
    
    public void makeQuizPaneVis(ActionEvent event) throws IOException{    
        makeQuizPane.setVisible(true);
        editOrMakePane.setVisible(false);
        
    }
    
    public void startMakingQuiz(ActionEvent event) throws IOException{
        String qTitle = quizTitle.getText();
        String qDesc = quizDesc.getText();
        boolean startquiz=startMakingQuizAfter(qTitle, qDesc);
        if(startquiz){
            subQuesPane.setVisible(true);
            makeQuizPane.setVisible(false);
        }
        else{
            lblErrorMake.setText("You must give a title and description for quiz");
        }
    }
    
    public boolean startMakingQuizAfter(String qTitle, String qDesc){    
        if(qTitle.equals("") || qDesc.equals("")){
            return false;
        }
        else{
            myQuiz.setTitle(qTitle);
            myQuiz.setDesc(qDesc);
            return true;
        }
    }
    public boolean startEditingQuiz(ActionEvent event) throws IOException{    
            
        String quizTitle = (String) cboEdit.getSelectionModel().getSelectedItem();
        for (Quiz quiz : quizList) {
            if(quizTitle.equals(quiz.getTitle())){
                myQuiz=quiz;
            }
	}
        if(myQuiz.getQuestList()!=null){
            editOrMakePane.setVisible(false);
            editQuizPane.setVisible(true);
            
            quesList = myQuiz.getQuestList();
            
            iter = quesList.iterator();
            
            currQues=iter.next();
            dispNextQues(currQues);
            return true;
        }
        else{
            System.out.println("NO QUESTIONS");
            return false;
        }
        
    }
    
    public void displayOptions(Question ques){
        if(ques instanceof mcqQuest){
            insMCQPane.setVisible(true);
            insTrueFalsePane.setVisible(false);
            insNumericPane.setVisible(false);
            
            radMCQa1.setText(((mcqQuest) ques).getoptA());
            radMCQb1.setText(((mcqQuest) ques).getoptB());
            radMCQc1.setText(((mcqQuest) ques).getoptC());
            radMCQd1.setText(((mcqQuest) ques).getoptD());
        }
        else if(ques instanceof truefalseQuest){
            insMCQPane.setVisible(false);
            insTrueFalsePane.setVisible(true);
            insNumericPane.setVisible(false);
            
            radTrue1.setText(((truefalseQuest) ques).getoptT());
            radFalse1.setText(((truefalseQuest) ques).getoptF());
            
        }
        else if(ques instanceof Question){
            insMCQPane.setVisible(false);
            insTrueFalsePane.setVisible(false);
            insNumericPane.setVisible(true);
           
        }
        
        String ans = currQues.getCorrectAnswer();
        
        if(insMCQPane.isVisible()){
            if(radMCQa1.getText().equals(ans)){
                radMCQa1.setSelected(true);
            }
            else if(radMCQb1.getText().equals(ans)){
                radMCQb1.setSelected(true);
            }
            else if(radMCQc1.getText().equals(ans)){
                radMCQc1.setSelected(true);
            }
            if(radMCQd1.getText().equals(ans)){
                radMCQd1.setSelected(true);
            }
        }
        else if(insTrueFalsePane.isVisible()){
            if(radTrue1.getText().equals(ans)){
                radTrue1.setSelected(true);
            }
            if(radFalse1.getText().equals(ans)){
                radFalse1.setSelected(true);
            }
        }
        else if(insNumericPane.isVisible()){
            txtAns.setText(ans);
        }
        
        
    }
    
    public void dispNextQues(Question ques){
        
        if(!iter.hasNext()){
            btnNext.setText("Finish");
        }
        
        lblQuesNum.setText("Question " + ++QuesNum);
        txtQues1.setText(ques.getQuest());
        lblMarks.setText("Max Marks ( " + ques.getMaxMarks() + " )" );
        displayOptions(ques);
        
    }
    
    public void makeQuiz(ActionEvent event) throws IOException{    
        boolean mQuiz=makeQuizAfter();
        if(mQuiz){
            quizFileStream qf = new quizFileStream();
            qf.writeToFile(quizList, filePath,0);
           
            Stage stage = (Stage) txtAns.getScene().getWindow();
            stage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Instructor.fxml"));
            Scene scene = new Scene(root,600,600);
            Stage primaryStage=new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        }
        else{
            lblErrorDone.setText("Enter atleast one question for quiz");
        }
    }
        
    
    public boolean makeQuizAfter(){    
        if(myQuiz.getQuestList().isEmpty()){
            return false;
        }
        else{
            quizList.add(myQuiz);
            return true;
            
        }
    }
    
    public void makeMCQ(ActionEvent event) throws IOException{    
        makeNumericPane.setVisible(false);
        makeTrueFalsePane.setVisible(false);
        makeMCQPane.setVisible(true);
    }
    
    public void makeTrueFalse(ActionEvent event) throws IOException{    
        makeMCQPane.setVisible(false);
        makeTrueFalsePane.setVisible(true);
        makeNumericPane.setVisible(false);
      
    }
    
    public void makeNumeric(ActionEvent event) throws IOException{    
        makeMCQPane.setVisible(false);
        makeTrueFalsePane.setVisible(false);
        makeNumericPane.setVisible(true);
      
    }
    
    public void gotoNextQues(ActionEvent event) throws IOException{    
        
        if(iter.hasNext()){
            index++;
            currQues=iter.next();
            dispNextQues(currQues);
        }
        else{
            editOrMakePane.setVisible(true);
            editQuizPane.setVisible(false);
            insMCQPane.setVisible(false);
            insTrueFalsePane.setVisible(false);
            insNumericPane.setVisible(false);
            
            myQuiz.setQuestList(quesList);
            
            quizFileStream qf = new quizFileStream();
            qf.writeToFile(quizList, filePath,1);
            
            Stage stage = (Stage) txtAns.getScene().getWindow();
            stage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Instructor.fxml"));
            Scene scene = new Scene(root,600,600);
            Stage primaryStage=new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }
    }
    
    public void subMCQ(ActionEvent event) throws IOException{    
        String quesMarks=txtMarks.getText();
        String quesStr=txtQuesMCQ.getText();
        String mcqA=txtMCQa.getText();
        String mcqB=txtMCQb.getText();
        String mcqC=txtMCQc.getText();
        String mcqD=txtMCQd.getText();
        
        boolean sMCQ = subMCQAfter(quesMarks,quesStr,mcqA,mcqB,mcqC,mcqD);
        if(sMCQ){
            mcqQuest myQues = new mcqQuest();
            myQues.setQuest(txtQuesMCQ.getText());
            myQues.setOptions(txtMCQa.getText(),txtMCQb.getText(),txtMCQc.getText(),txtMCQd.getText());
            if(radMCQa.isSelected()){
                myQues.setCorrectAnswer(txtMCQa.getText());
            }
            else if(radMCQb.isSelected()){
                myQues.setCorrectAnswer(txtMCQb.getText());
            }
            else if(radMCQc.isSelected()){
                myQues.setCorrectAnswer(txtMCQc.getText());
            }
            else if(radMCQd.isSelected()){
                myQues.setCorrectAnswer(txtMCQd.getText());
            }
            else{
                lblErrorMCQ.setText("You must select a correct answer");
            }
            myQues.setMaxMarks(Integer.parseInt(txtMarks.getText()));
            
            txtQuesMCQ.setText("");
            txtMCQa.setText("");
            txtMCQb.setText("");
            txtMCQc.setText("");
            txtMCQd.setText("");
            txtMarks.setText("");
            lblErrorDone.setText("");
            lblErrorMCQ.setText("");
            lblErrorNumeric.setText("");
            lblErrorTrueFalse.setText("");
            
            
            myQuiz.addQues(myQues);
            
        }
        else{
            if(txtMarks.getText().equals("")){
                lblErrorMCQ.setText("You must enter Max Marks.");
            }
            lblErrorMCQ.setText("You must enter a question and all mcq options.");
        }
           
    }
    
    public boolean subMCQAfter(String qMarks,String qQues, String qMCQa, String qMCQb, String qMCQc, String qMCQd) {    
        if(qMarks.equals("") || qQues.equals("") || qMCQa.equals("") || qMCQb.equals("") || qMCQc.equals("") || qMCQd.equals("")){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void subTrueFalse(ActionEvent event) throws IOException{    
        if(txtMarks.getText().isEmpty() || txtQuesTrueFalse.getText().isEmpty()){
            if(txtMarks.getText().equals("")){
                lblErrorMCQ.setText("You must enter Max Marks.");
            }
            lblErrorTrueFalse.setText("You must enter a question and mark its correct answer.");
        
        }
        else{
            truefalseQuest myQues = new truefalseQuest();
            myQues.setQuest(txtQuesTrueFalse.getText());
            myQues.setOptions("True","False");
            if(radTrue.isSelected()){
                myQues.setCorrectAnswer("True");
            }
            else if(radFalse.isSelected()){
                myQues.setCorrectAnswer("False");
            }
            else{
                lblErrorTrueFalse.setText("You must select a correct answer");
            }
            myQues.setMaxMarks(Integer.parseInt(txtMarks.getText()));
            
            txtQuesTrueFalse.setText("");
            txtMarks.setText("");
            
            myQuiz.addQues(myQues);
            
        }
    }
    
    public void subNumeric(ActionEvent event) throws IOException{    
        if(txtQuesNumeric.getText().isEmpty() || txtAnsNumeric.getText().isEmpty() || txtMarks.getText().isEmpty()){
            if(txtMarks.getText().equals("")){
                lblErrorNumeric.setText("You must enter Max Marks.");
            }
            lblErrorNumeric.setText("You must enter a question and its answer.");
        
        }
        else{
            Question myQues = new Question();
            myQues.setQuest(txtQuesNumeric.getText());
            myQues.setCorrectAnswer(txtAnsNumeric.getText());
            myQues.setMaxMarks(Integer.parseInt(txtMarks.getText()));
            
            txtQuesNumeric.setText("");
            txtAnsNumeric.setText("");
            txtMarks.setText("");
            
            myQuiz.addQues(myQues);
            
        }
    }
    
    public void chngQuesIndex(ActionEvent event) throws Exception{
        int newIndex = Integer.parseInt(txtchngIndex.getText());
        newQuesList=chngQuesIndexAfter(newIndex,currQues);
        
    }
    
    public List<Question> chngQuesIndexAfter(int newInd,Question currQues){
        old = quesList.get(--newInd);
        Collections.swap(quesList, newInd, index);
        return quesList;
    }
}
