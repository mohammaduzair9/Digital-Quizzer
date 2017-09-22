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
    
    user user1 = user1=new user("uzair","1234","Student",0);
    user user2=new user("fahad","1234","Instructor",0);
    
    user User = new user();
    
    public void Login(ActionEvent event) throws Exception{
        String username=txtUser.getText();
        String userpass=txtPass.getText();
        User=tryLogin(username,userpass);
        
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
    
    public user tryLogin(String username,String password){
        
        if(username.equals(user1.getUserName()) && password.equals(user1.getPassword())){
            return user1;
        }
        else if(username.equals(user2.getUserName()) && password.equals(user2.getPassword())){
            return user2;
        }
        else{
            return null;
        }
    }
    
    
    
}
