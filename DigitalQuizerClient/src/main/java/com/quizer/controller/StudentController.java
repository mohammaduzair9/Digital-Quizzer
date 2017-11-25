package com.quizer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//Controller Class For Student
public class StudentController implements Initializable {
 
    @FXML    public ComboBox<String> cboSelectQuiz;
    @FXML    private Label lblQuizDesc;
    @FXML    private Label lblQuesNum;
    @FXML    private Label lblMarks;
    @FXML    private Label lblfinalTitle;
    @FXML    private Label lblfinalScr;
    @FXML    private Label lblfinalMaxScr;
    @FXML    private Pane stdMCQPane;
    @FXML    private Pane stdTrueFalsePane;
    @FXML    private Pane stdNumericPane;
    @FXML    private Pane stdQuesPane;
    @FXML    private Pane stdSelectPane;
    @FXML    private Pane stdfinalPane;
    @FXML    private TextArea txtQues;
    @FXML    private TextArea txtAns;
    @FXML    private Button btnNext;
    @FXML    private Button btnAttemptNew;
    @FXML    private Button btnExit;
    @FXML    private RadioButton radMCQa;
    @FXML    private RadioButton radMCQb;
    @FXML    private RadioButton radMCQc;
    @FXML    private RadioButton radMCQd;
    @FXML    private RadioButton radTrue;
    @FXML    private RadioButton radFalse;
    @FXML    private ToggleGroup mcqGrp;
    @FXML    private ToggleGroup truefalseGrp;
        
    //ObservableList<String> cboList = FXCollections.observableArrayList();
    
    //Called When Class initialized
    @Override
    public void initialize(URL location,ResourceBundle resources){
        cboSelectQuiz.getItems().clear();
        
        //request server to return all quizes
        //cboEdit.getItems().add();
    }
    
    //Change Description of a Quiz
    public void changeQuizDesc(ActionEvent event) throws IOException{    
        //String quizTitle = cboSelectQuiz.getSelectionModel().getSelectedItem();
        
        //change quiz description when selected from list
        //lblQuizDesc.setText("");    
        
        
    }
    
    public void attemptNewQuiz(ActionEvent event) throws IOException{    
        stdfinalPane.setVisible(false);
        stdSelectPane.setVisible(true);
    }
    
    public void exit(ActionEvent event) throws IOException{    
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    
    public void startQuiz(ActionEvent event) throws IOException{    
        
        //int quizID = (int) cboSelectQuiz.getSelectionModel().getSelectedItem();
        
        //request server to return questions by giving in quiz ID
        
        stdSelectPane.setVisible(false);
        stdQuesPane.setVisible(true);
            
        btnNext.setText("Next");
        
    }
    
    public void gotoNextQues(ActionEvent event) throws IOException{    
        
        //function to go to next question
        
    }
    
    public void dispScore(){
        stdQuesPane.setVisible(false);
        stdfinalPane.setVisible(true);
        
        //function to display score
        lblfinalScr.setText("");
        lblfinalMaxScr.setText("");
    }
    
    public void displayOptions(){
        
        //displaying options of question
        
    }
    
    public void dispNextQues(){
        
        btnNext.setText("Finish");
        
        lblQuesNum.setText("Question " );
        txtQues.setText("");
        lblMarks.setText("Max Marks ( " + 5 + " )" );
        //displayOptions(ques);
 
    } 
}
