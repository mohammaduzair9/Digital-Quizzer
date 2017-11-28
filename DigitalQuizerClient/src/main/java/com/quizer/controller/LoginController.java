package com.quizer.controller;

import com.quizer.Bo.UserBo;
import com.quizer.model.User;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//Controller Class for Login
public class LoginController  {
    
    @FXML    private Label lblStatus;
    @FXML    private Label lblErrorMake;
    @FXML    private Label lblErrorDone;    
    @FXML    private Label lblErrorMCQ;
    @FXML    private Label lblErrorTrueFalse;
    @FXML    private Label lblErrorNumeric;
    @FXML    private TextField txtUser;
    @FXML    private TextField txtPass;
    @FXML    private TextField quizTitle;
    @FXML    private TextArea quizDesc;
    @FXML    private TextArea txtQuesMCQ;
    @FXML    private TextArea txtQuesTrueFalse;
    @FXML    private TextArea txtQuesNumeric;
    @FXML    private TextArea txtAnsNumeric;
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
    @FXML    private Pane makeQuizPane;
    @FXML    private Pane subQuesPane;
    @FXML    private Pane makeMCQPane;
    @FXML    private Pane makeTrueFalsePane;
    @FXML    private Pane makeNumericPane;
    @FXML    private Pane stdSelectPane;
    @FXML    private Pane editOrMakePane;
    @FXML    private ComboBox cboEdit;
    @FXML    private Button btnMakeNew;
    @FXML    private Button btnEditQuiz;
    
    UserBo userbo = new UserBo();
    User user = new User();
    
    //Function Called When Login Clicked
    public void Login(ActionEvent event) throws Exception{
        String username=txtUser.getText();
        String userpass=txtPass.getText();
        
        user = userbo.getUser(username,userpass);
        
        if(user != null){
            lblStatus.setText("Login Successful");
        
            if(user.getType().equals("instructor")){
        
                Stage stage = (Stage) lblStatus.getScene().getWindow();
                stage.close();
        
                Stage primaryStage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Instructor.fxml"));
                
                Scene scene = new Scene(root,600,600);
                primaryStage.setScene(scene);
                primaryStage.show();
        
            }
            else if(user.getType().equals("student")){
        
                Stage stage = (Stage) lblStatus.getScene().getWindow();
                stage.close();
        
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Student.fxml"));
        
                Scene scene = new Scene(root,600,600);
                primaryStage.setScene(scene);
                primaryStage.show();     
        
            }
        }
        else{
            lblStatus.setText("Login Failed");
        }
 
    }
 
}
