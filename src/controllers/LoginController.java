package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.UserDatas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    /**
     * saving user's data who has login
     */
    public static UserDatas loginUser;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label ErrorLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }

    @FXML
    private void loginClick(ActionEvent actionEvent)
    {
        String userName = username.getText();
        String passWord = password.getText();

        for (UserDatas userData : Main.userDatas)
        {
            if(userData.getPassword().equals(passWord) && userData.getUsername().equals(userName))
            {
                try
                {
                    loginUser = userData;
                    openMenu(actionEvent);
                }
                catch(Exception e)
                {
                }
            }

            else
            {
                ErrorLBL.setText("invalid");
            }
        }
    }

    private void openMenu(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("MainMenu");
    }

    @FXML
    private void signUpClick(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("SignUp");
    }

    @FXML
    private void exitAction(ActionEvent actionEvent)
    {
        System.exit(0);
    }
}
