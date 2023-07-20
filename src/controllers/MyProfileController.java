package controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MyProfileController implements Initializable
{
    @FXML
    private Label NameLable;

    @FXML
    private JFXTextArea bioField;

    @FXML
    private Label saveLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // set username and bio
        String name = LoginController.loginUser.getUsername();
        NameLable.setText(name);

        String bio = LoginController.loginUser.getBio();
        bioField.setText(bio);
    }

    @FXML
    private void saveAction(ActionEvent actionEvent)
    {
        String bio = bioField.getText();

        if(bio.length() > 70)
        {
            saveLBL.setText("too long");
        }

        else
        {
            bio = bio.replaceAll("'", "''");

            saveLBL.setText("save successful");

            // save changes in user
            LoginController.loginUser.setBio(bio);

            try
            {
                saveInDataBase(bio);

                // save changes in main array list
                new Main().init();
            }
            catch(Exception e)
            {
            }
        }
    }

    /**
     * saving bio in data base
     * @param bio
     * @throws Exception
     */
    private void saveInDataBase(String bio) throws Exception
    {
        String query = "update user set bio='%s' where userName='%s'";
        query = String.format(query, bio, LoginController.loginUser.getUsername());
        DataBaseController.openDataBase(query);
    }

    @FXML
    private void menuAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("MainMenu");
    }
}
