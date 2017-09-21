/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myquizer;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author UZAIR
 */
public class StudentMainController implements Initializable {
 
    @FXML
    public ComboBox<String> cboSelectQuiz;
    @FXML
    private Label lblQuizDesc;
    @FXML
    private Label lblQuesNum;
    @FXML
    private Label lblMarks;
    @FXML
    private Label lblfinalTitle;
    @FXML
    private Label lblfinalScr;
    @FXML
    private Label lblfinalMaxScr;
    @FXML
    private Pane stdMCQPane;
    @FXML
    private Pane stdTrueFalsePane;
    @FXML
    private Pane stdNumericPane;
    @FXML
    private Pane stdQuesPane;
    @FXML
    private Pane stdSelectPane;
    @FXML
    private Pane stdfinalPane;
    @FXML
    private TextArea txtQues;
    @FXML
    private TextArea txtAns;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnAttemptNew;
    @FXML
    private Button btnExit;
    @FXML
    private RadioButton radMCQa;
    @FXML
    private RadioButton radMCQb;
    @FXML
    private RadioButton radMCQc;
    @FXML
    private RadioButton radMCQd;
    @FXML
    private RadioButton radTrue;
    @FXML
    private RadioButton radFalse;
    @FXML
    private ToggleGroup mcqGrp;
    @FXML
    private ToggleGroup truefalseGrp;
    
    
    List<Quiz> quizList;
    ObservableList<String> cboList = FXCollections.observableArrayList();
    String filePath = "Quizes.ser";
    Quiz myQuiz;
    int QuesNum;
    List<Question> quesList;
    Iterator<Question> iter;
    int Score;
    int maxScr;
    Question currQues;
                    
    
    @Override
    public void initialize(URL location,ResourceBundle resources){
        Score=0;
        maxScr=0;
        QuesNum=0;
        currQues=null;
        quizFileStream qf = new quizFileStream();
        quizList = qf.readFromFile(filePath);
        for (Quiz quiz : quizList) {
            System.out.println(quiz.getTitle());
            cboSelectQuiz.getItems().add(quiz.getTitle());
	}
    }
    
    public void changeQuizDesc(ActionEvent event) throws IOException{    
        String quizTitle = cboSelectQuiz.getSelectionModel().getSelectedItem();
        for (Quiz quiz : quizList) {
            if(quizTitle.equals(quiz.getTitle())){
                lblQuizDesc.setText(quiz.getDesc());    
            }
	}
        
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
        String quizTitle = cboSelectQuiz.getSelectionModel().getSelectedItem();
        for (Quiz quiz : quizList) {
            if(quizTitle.equals(quiz.getTitle())){
                myQuiz=quiz;
                
            }
	}
        if(myQuiz.getQuestList()!=null){
            stdSelectPane.setVisible(false);
            stdQuesPane.setVisible(true);
            
            
            quesList = myQuiz.getQuestList();
            iter = quesList.iterator();
            
            currQues=iter.next();
            dispNextQues(currQues);
            
        }
        else{
            System.out.println("NO QUESTIONS");
        }
    }
    
    public void gotoNextQues(ActionEvent event) throws IOException{    
        System.out.println(currQues);
        System.out.println(maxScr);
        System.out.println(Score);
        maxScr+=currQues.getMaxMarks();
        String ans = currQues.getCorrectAnswer();
        
        if(stdMCQPane.isVisible()){
            if(radMCQa.isSelected() && radMCQa.getText().equals(ans)){
                Score+=currQues.getMaxMarks();           
            }
            else if(radMCQb.isSelected() && radMCQb.getText().equals(ans)){
                Score+=currQues.getMaxMarks();           
            }
            else if(radMCQc.isSelected() && radMCQc.getText().equals(ans)){
                Score+=currQues.getMaxMarks();           
            }
            else if(radMCQd.isSelected() && radMCQd.getText().equals(ans)){
                Score+=currQues.getMaxMarks();           
            }
        }
        else if(stdTrueFalsePane.isVisible()){
            if(radTrue.isSelected() && radTrue.getText().equals(ans)){
                Score+=currQues.getMaxMarks();           
            }
            else if(radFalse.isSelected() && radFalse.getText().equals(ans)){
                Score+=currQues.getMaxMarks();           
            }
        }
        else if(stdNumericPane.isVisible()){
            if(currQues.getCorrectAnswer().equals(txtAns.getText())){
                Score+=currQues.getMaxMarks();           
            }
        }
        
        
        if(iter.hasNext()){
            currQues=iter.next();
            System.out.println(currQues);
            dispNextQues(currQues);
        }
        else{
            dispScore();
        }
    }
    
    public void dispScore(){
        stdQuesPane.setVisible(false);
        stdfinalPane.setVisible(true);
        
        lblfinalTitle.setText("End of " + myQuiz.getTitle());
        lblfinalScr.setText(""+Score);
        lblfinalMaxScr.setText(""+maxScr);
    }
    
    public void displayOptions(Question ques){
        if(ques instanceof mcqQuest){
            stdMCQPane.setVisible(true);
            stdTrueFalsePane.setVisible(false);
            stdNumericPane.setVisible(false);
            
            radMCQa.setText(((mcqQuest) ques).getoptA());
            radMCQb.setText(((mcqQuest) ques).getoptB());
            radMCQc.setText(((mcqQuest) ques).getoptC());
            radMCQd.setText(((mcqQuest) ques).getoptD());
        }
        else if(ques instanceof truefalseQuest){
            stdMCQPane.setVisible(false);
            stdTrueFalsePane.setVisible(true);
            stdNumericPane.setVisible(false);
            
            radTrue.setText(((truefalseQuest) ques).getoptT());
            radFalse.setText(((truefalseQuest) ques).getoptF());
            
        }
        else if(ques instanceof Question){
            stdMCQPane.setVisible(false);
            stdTrueFalsePane.setVisible(false);
            stdNumericPane.setVisible(true);
           
        }
    }
    
    public void dispNextQues(Question ques){
        
        if(!iter.hasNext()){
            btnNext.setText("Finish");
        }
        
        lblQuesNum.setText("Question " + ++QuesNum);
        txtQues.setText(ques.getQuest());
        lblMarks.setText("Max Marks ( " + ques.getMaxMarks() + " )" );
        displayOptions(ques);
 
    } 
}
