package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Tweet;

import java.net.URL;
import java.util.ResourceBundle;

public class TweetPropertiesController implements Initializable
{
    static Tweet tweet;

    @FXML
    private Label timeLBL, dateLBL, likeLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // set properties
        timeLBL.setText(tweet.getTime());
        dateLBL.setText(tweet.getDate());
        likeLBL.setText(tweet.getLikes());
    }

    @FXML
    private void closeAction(ActionEvent actionEvent)
    {
        PagesController.closePage(actionEvent);
    }
}
