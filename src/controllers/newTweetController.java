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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.Tweet;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class newTweetController implements Initializable
{
    @FXML
    private Label errorLBL, saveLBL;

    @FXML
    private JFXTextArea tweetField;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }

    public void saveAction(ActionEvent actionEvent)
    {
        if(tweetField.getText().length() > 140)
        {
            errorLBL.setText("your tweet must be less than 140 letters");
        }

        else if(tweetField.getText().length() == 0)
        {
            errorLBL.setText("write your tweet !!");
        }

        else
        {
            errorLBL.setText("");
            saveLBL.setText("save successful");
            tweetField.setDisable(true);

            String tweet = tweetField.getText();

            Tweet newTweet = new Tweet(tweet);

            LoginController.loginUser.getUserTweets().add(newTweet);

            try
            {
                saveInDataBase();
                new Main().init();
            }
            catch(Exception e)
            {
            }

        }
    }

    private void saveInDataBase() throws Exception
    {
        String query = "update user set Tweets='%s',Ids='%s',Dates='%s',Times='%s',Likes='%s' where userName='%s'";

        String tweets = "", ids = "", dates = "", times = "", likes = "";

        for (Tweet userTweet : LoginController.loginUser.getUserTweets())
        {
            tweets += userTweet.getTweet().replaceAll("'", "''") + "<flag>";
            ids += userTweet.getId() + "<flag>";
            dates += userTweet.getDate() + "<flag>";
            times += userTweet.getTime() + "<flag>";
            likes += userTweet.getLikes() + "<flag>";
        }

        query = String.format(query, tweets, ids, dates, times, likes, LoginController.loginUser.getUsername());
        DataBaseController.openDataBase(query);
    }

    @FXML
    private void menuAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("MainMenu");
    }
}

