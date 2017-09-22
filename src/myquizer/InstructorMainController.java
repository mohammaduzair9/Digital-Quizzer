/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myquizer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author UZAIR
 */
public class InstructorMainController implements Initializable {
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblQuesNum;
    @FXML
    private Label lblMarks;
    @FXML
    private Label lblErrorMake;
    @FXML
    private Label lblErrorDone;    
    @FXML
    private Label lblErrorMCQ;
    @FXML
    private Label lblErrorTrueFalse;
    @FXML
    private Label lblErrorNumeric;
    @FXML
    private TextArea txtQues1;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField quizTitle;
    @FXML
    private TextField txtchngIndex;
    @FXML
    private TextArea quizDesc;
    @FXML
    private TextArea txtQuesMCQ;
    @FXML
    private TextArea txtQuesTrueFalse;
    @FXML
    private TextArea txtQuesNumeric;
    @FXML
    private TextArea txtAnsNumeric;
    @FXML
    private TextArea txtAns;
    @FXML
    private TextField txtMCQa;
    @FXML
    private TextField txtMCQb;
    @FXML
    private TextField txtMCQc;
    @FXML
    private TextField txtMCQd;
    @FXML
    private TextField txtMarks;
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
    private RadioButton radMCQa1;
    @FXML
    private RadioButton radMCQb1;
    @FXML
    private RadioButton radMCQc1;
    @FXML
    private RadioButton radMCQd1;
    @FXML
    private RadioButton radTrue1;
    @FXML
    private RadioButton radFalse1;
    @FXML
    private Pane makeQuizPane;
    @FXML
    private Pane subQuesPane;
    @FXML
    private Pane makeMCQPane;
    @FXML
    private Pane makeTrueFalsePane;
    @FXML
    private Pane makeNumericPane;
    @FXML
    private Pane stdSelectPane;
    @FXML
    private Pane editOrMakePane;
    @FXML
    private ComboBox cboEdit;
    @FXML
    private Button btnMakeNew;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnEditQuiz;
    @FXML
    private Pane insNumericPane;
    @FXML
    private Pane insMCQPane;
    @FXML
    private Pane insTrueFalsePane;
    @FXML
    private Pane editQuizPane;
    
    int QuesNum;
    int index=1;
    List<Question> quesList=new CopyOnWriteArrayList<>();
    Question old;
    Iterator<Question> iter;
    Question currQues;
    private Quiz myQuiz = new Quiz();;
    List<Quiz> quizList = new ArrayList<>();
    String filePath = "Quizes.ser";
    String role="Instructor";
    
    user user1 = new user();
    user user2 = new user();
    user User = new user();
    
    @Override
    public void initialize(URL location,ResourceBundle resources){
        cboEdit.getItems().clear();
        quizFileStream qf = new quizFileStream();
        quizList = qf.readFromFile(filePath);
        for (Quiz quiz : quizList) {
            System.out.println(quiz.getTitle());
            cboEdit.getItems().add(quiz.getTitle());
	}
    }
    
    public void makeQuizPaneVis(ActionEvent event) throws IOException{    
        makeQuizPane.setVisible(true);
        editOrMakePane.setVisible(false);
        
    }
    
    public void startMakingQuiz(ActionEvent event) throws IOException{    
        if(quizTitle.getText().isEmpty() || quizDesc.getText().isEmpty()){
            lblErrorMake.setText("You must give a title and description for quiz");
        }
        else{
            
            myQuiz.setTitle(quizTitle.getText());
            myQuiz.setDesc(quizDesc.getText());
            
            subQuesPane.setVisible(true);
            makeQuizPane.setVisible(false);
            
        }
    }
    public void startEditingQuiz(ActionEvent event) throws IOException{    
            
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
            
        }
        else{
            System.out.println("NO QUESTIONS");
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
        if(false){
            lblErrorDone.setText("Enter atleast one question for quiz");
        }
        else{
            quizList.add(myQuiz);
            quizFileStream qf = new quizFileStream();
            qf.writeToFile(quizList, filePath,0);
            
            Stage stage = (Stage) txtAns.getScene().getWindow();
            stage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/myquizer/Main.fxml"));
            Scene scene = new Scene(root,600,600);
            Stage primaryStage=new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
            
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
            
            Parent root = FXMLLoader.load(getClass().getResource("/myquizer/Main.fxml"));
            Scene scene = new Scene(root,600,600);
            Stage primaryStage=new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }
    }
    
    public void subMCQ(ActionEvent event) throws IOException{    
        if(txtMarks.getText().isEmpty() || txtQuesMCQ.getText().isEmpty() || txtMCQa.getText().isEmpty() || txtMCQb.getText().isEmpty() || txtMCQc.getText().isEmpty() || txtMCQd.getText().isEmpty()){
            lblErrorMCQ.setText("You must enter a question and all mcq options with the max marks.");
        }
        else{
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
            
            myQuiz.addQues(myQues);
            
        }
    }
    
    public void subTrueFalse(ActionEvent event) throws IOException{    
        if(txtMarks.getText().isEmpty() || txtQuesTrueFalse.getText().isEmpty()){
            lblErrorTrueFalse.setText("You must enter a question and max marks.");
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
            lblErrorNumeric.setText("You must enter a question and its answer and max marks");
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
        old = quesList.get(newIndex);
        quesList.set(newIndex, currQues);
        quesList.set(index, old);
    }
}
