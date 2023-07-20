package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }

    @FXML
    private void myProfileAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("prof");
    }

    @FXML
    private void newTweetAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("newTweet");
    }

    @FXML
    private void myTweetsAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("MyTweets");
    }

    @FXML
    private void followersAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("Followers");
    }

    @FXML
    private void followingAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("Following");
    }

    @FXML
    private void timeLineAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("Timeline");
    }

    @FXML
    private void logOutAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("Login");
    }

    @FXML
    private void exitAcion(ActionEvent actionEvent)
    {
        System.exit(0);
    }
}
