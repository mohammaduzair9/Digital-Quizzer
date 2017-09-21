/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myquizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class MainController  {
    
    @FXML
    private Label lblStatus;
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
    private TextField txtUser;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField quizTitle;
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
    private Button btnEditQuiz;
    
    private Quiz myQuiz = new Quiz();;
    List<Quiz> quizList = new ArrayList<>();
    String filePath = "Quizes.ser";
    String role="Instructor";
    
    user user1 = new user();
    user user2 = new user();
    user User = new user();
    
    public void Login(ActionEvent event) throws Exception{
        user1=new user("uzair","1234","Student",0);
        user2=new user("fahad","1234","Instructor",0);
        
        if(txtUser.getText().equals(user1.getUserName()) && txtPass.getText().equals(user1.getPassword())){
            User=user1;
        }
        else if(txtUser.getText().equals(user2.getUserName()) && txtPass.getText().equals(user2.getPassword())){
            User=user2;
        }
        
        System.out.println(User);
        if(User==user1 || User==user2){
            lblStatus.setText("Login Successful");
            
            Stage stage = (Stage) lblStatus.getScene().getWindow();
            stage.close();
            
            Stage primaryStage=new Stage();
            if(User.getRole().equals("Instructor")){
                Parent root = FXMLLoader.load(getClass().getResource("/myquizer/Main.fxml"));
                
                Scene scene = new Scene(root,600,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            else if(User.getRole().equals("Student")){
                Parent root = FXMLLoader.load(getClass().getResource("/myquizer/StudentMain.fxml"));
                Scene scene = new Scene(root,600,600);
                primaryStage.setScene(scene);
                primaryStage.show();     
            }
            
        }
        else{
            lblStatus.setText("Login Failed");
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
    
    public void makeQuiz(ActionEvent event) throws IOException{    
        if(false){
            lblErrorDone.setText("Enter atleast one question for quiz");
        }
        else{
            quizList.add(myQuiz);
            quizFileStream qf = new quizFileStream();
            qf.writeToFile(quizList, filePath);
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
    
    public void btnQuiz1(ActionEvent event) throws Exception{
        
    }
}
