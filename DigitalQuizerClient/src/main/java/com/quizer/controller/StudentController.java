package com.quizer.controller;

import com.quizer.Bo.QuestionBo;
import com.quizer.Bo.QuizBo;
import com.quizer.model.Question;
import com.quizer.model.Quiz;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.util.StringConverter;

//Controller Class For Student
public class StudentController implements Initializable {
 
    @FXML    public ComboBox<Quiz> cboSelectQuiz;
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
        
    QuizBo quizbo = new QuizBo();
    QuestionBo questionbo = new QuestionBo();
    List<Quiz> quizes = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    
    //Called When Class initialized
    @Override
    public void initialize(URL location,ResourceBundle resources){
        
        cboSelectQuiz.getItems().clear();
    
        //request server to return all quizes
        quizes = quizbo.getQuizes();
        
        //populating the quiz combobox
        ObservableList<Quiz> quizOptions = FXCollections.observableArrayList();
        quizes.forEach((Quiz quiz) -> {
            quizOptions.add(quiz);
        });
        
        cboSelectQuiz.setItems(quizOptions);
        cboSelectQuiz.setConverter(new StringConverter<Quiz>(){

            @Override
            public String toString(Quiz object) {
                return object.getTitle();
            }

            @Override
            public Quiz fromString(String string) {
                return cboSelectQuiz.getItems().stream().filter(quiz -> 
                    quiz.getTitle().equals(string)).findFirst().orElse(null);
            }
        });
        
        //Change Description of a Quiz
        cboSelectQuiz.valueProperty().addListener((obs, oldval, newval) -> {
            lblQuizDesc.setText(newval.getDescription());
        });
        
    }
    
    public void changeQuizDesc(ActionEvent event) throws IOException{    
        
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
        
        //select id of selected quiz
        int quizID = (int) cboSelectQuiz.getSelectionModel().getSelectedItem().getId();
        
        //request server to return questions by giving in quiz ID
        questions = questionbo.getQuestions(quizID); 
        
        questions.forEach((Question question) -> {
            System.out.println(question);
        });
        
        
        
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
