package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Tweet;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FollowersController implements Initializable
{
    @FXML
    private JFXListView list;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        list.setItems(FXCollections.observableArrayList(LoginController.loginUser.getFollowers()));
    }

    @FXML
    private void menuAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("MainMenu");
    }
}
