package controllers;

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

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController implements Initializable
{
    @FXML
    private TextField newUsername;

    @FXML
    private PasswordField newPassword;

    @FXML
    private Label ErrorLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }

    @FXML
    private void loginAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("Login");
    }

    @FXML
    private void signUpAction(ActionEvent actionEvent) throws Exception
    {
        boolean flag = true;

        // range ->    1 <= username.length <= 20
        String newUser = newUsername.getText();

        // range ->    8 <= password.length <= 20
        String newPass = newPassword.getText();

        if(!newUser.matches("(\\w|_)*"))
        {
            ErrorLBL.setText("invalid username");
            flag = false;
        }

        else if(newUser.length() > 20)
        {
            ErrorLBL.setText("Long username !");
            flag = false;
        }

        else if(newUser.length() <= 0)
        {
            ErrorLBL.setText("Short username !");
            flag = false;
        }

        else if(newPass.length() < 8)
        {
            ErrorLBL.setText("Short password !");
            flag = false;
        }

        else if(!newPass.matches("(\\w|\\d|_)*"))
        {
            ErrorLBL.setText("invalid password");
            flag = false;
        }

        else if(newPass.length() > 20)
        {
            ErrorLBL.setText("Long password !");
            flag = false;
        }

        for (UserDatas userData : Main.userDatas)
        {
            if(userData.getUsername().equals(newUser) || userData.getPassword().equals(newPass))
            {
                ErrorLBL.setText("already used\ntry another");
                flag = false;
            }
        }


        if(flag)
        {
            // creating a new user
            UserDatas user = new UserDatas(newUser, newPass, "");

            // adding user to users in main array list
            Main.userDatas.add(user);

            // updating data base :

            String query = "insert into user(userName,password) values('%s','%s')";
            query = String.format(query, user.getUsername(), user.getPassword());
            DataBaseController.openDataBase(query);

            ErrorLBL.setText("Sign up successful\nback to Login page");

            newUsername.setDisable(true);
            newPassword.setDisable(true);
        }
    }

    @FXML
    private void exitAction(ActionEvent actionEvent)
    {
        System.exit(0);
    }
}
