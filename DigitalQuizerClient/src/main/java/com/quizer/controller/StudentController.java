package com.quizer.controller;

import com.quizer.Bo.QuestionBo;
import com.quizer.Bo.QuizBo;
import com.quizer.model.Mcq;
import com.quizer.model.Question;
import com.quizer.model.Quiz;
import com.quizer.model.TrueFalse;
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
    
    List<Question> questions = new ArrayList<>();
    Question quest = null;
    
    int userScore = 0;
    int maxScore = 0;
    
    //Called When Class initialized
    @Override
    public void initialize(URL location,ResourceBundle resources){
        
        cboSelectQuiz.getItems().clear();
    
        //request server to return all quizes and populating the quiz combobox
        ObservableList<Quiz> quizOptions = FXCollections.observableArrayList();
        quizbo.getQuizes().forEach((Quiz quiz) -> {
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
        
        if(questions!=null){
            
            stdSelectPane.setVisible(false);
            stdQuesPane.setVisible(true);
            
            ActionEvent evnt = new ActionEvent();
            gotoNextQues(evnt);
            
        }
        else{
            lblQuizDesc.setText("NO QUESTIONS IN THIS QUIZ");
        }
       
    }
    
    public void gotoNextQues(ActionEvent event) throws IOException{    
       
        //getting the question number
        int quesNum = 1;
        if(quest != null)
            quesNum = questions.indexOf(quest) + 2;
        
        //marking the question
        if(quesNum>1)
            markQuestion();
        
        //if no more questions then end the quiz
        if(quesNum == questions.size()+1)
            endQuiz();
        
        else{
            if(quesNum == questions.size())
                btnNext.setText("Finish");
            else
                btnNext.setText("Next");
        
            quest = questions.get(quesNum-1);
            lblQuesNum.setText("Question " + quesNum);
            txtQues.setText(quest.getQuestion());
            lblMarks.setText("Max Marks ( " + quest.getMarks() + " )" );
            
            
            if(quest instanceof TrueFalse)
                showTrueFalse(quest);
            
            else if (quest instanceof Mcq)
                showMcq(quest);
            
            else 
                showNumeric(quest);
        
        }
    }
    
    public void markQuestion(){
       
        String correctAnswer = quest.getAnswer();
        String userAns;
        
        if(quest instanceof TrueFalse)
            userAns = truefalseGrp.getSelectedToggle().getUserData().toString();
        
        else if(quest instanceof Mcq)
            userAns = mcqGrp.getSelectedToggle().getUserData().toString();
        
        else 
            userAns = txtAns.getText();
        
        maxScore += quest.getMarks();
        if(userAns.equals(correctAnswer))
            userScore += quest.getMarks();
       
    } 

    private void showMcq(Question quest) {
        stdMCQPane.setVisible(true);
        stdTrueFalsePane.setVisible(false);
        stdNumericPane.setVisible(false);
            
        radMCQa.setText(((Mcq)quest).getOptionA());
        radMCQb.setText(((Mcq)quest).getOptionB());
        radMCQc.setText(((Mcq)quest).getOptionC());
        radMCQd.setText(((Mcq)quest).getOptionD());
       
    }

    private void showTrueFalse(Question quest) {
        stdMCQPane.setVisible(false);
        stdTrueFalsePane.setVisible(true);
        stdNumericPane.setVisible(false);
            
        radTrue.setText(((TrueFalse)quest).getOptionTrue());
        radFalse.setText(((TrueFalse)quest).getOptionFalse());
    
    }

    private void showNumeric(Question quest) {
        stdMCQPane.setVisible(false);
        stdTrueFalsePane.setVisible(false);
        stdNumericPane.setVisible(true);
    
    }
    
    public void endQuiz(){
        stdQuesPane.setVisible(false);
        stdfinalPane.setVisible(true);
        
        //function to display score
        lblfinalScr.setText( "" + userScore);
        lblfinalMaxScr.setText( "" + maxScore);
    }

}
