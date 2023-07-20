package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import sample.Tweet;
import sample.UserDatas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class TimeLineController implements Initializable
{
    @FXML
    private JFXListView list;

    @FXML
    private JFXButton propBTN;

    private String listTweet;

    private ArrayList<Tweet> tweets = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // my tweets :
        for (Tweet userTweet : LoginController.loginUser.getUserTweets())
        {
            tweets.add(userTweet);
        }

        // my following tweets :
        LoginController.loginUser.setFollowingUsers();

        for (UserDatas followingUser : LoginController.loginUser.getFollowingUsers())
        {
            for (Tweet userTweet : followingUser.getUserTweets())
            {
                tweets.add(userTweet);
            }
        }

        Collections.sort(tweets);

        ArrayList<String> Tweets = new ArrayList<>();

        for (Tweet tweet : tweets)
        {
            Tweets.add(tweet.getTweet());
        }

        // set tweets in a list to show
        list.setItems(FXCollections.observableArrayList(Tweets));
    }

    @FXML
    private void menuAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("MainMenu");
    }

    @FXML
    private void listClicked(MouseEvent mouseEvent)
    {
        try
        {
            listTweet = list.getSelectionModel().getSelectedItem().toString();
            propBTN.setDisable(false);
        }

        catch (Exception e)
        {
        }
    }

    @FXML
    private void propAction(ActionEvent actionEvent) throws Exception
    {
        TweetPropertiesController.tweet = search();

        PagesController.openPage("tweetProperties");
    }

    private Tweet search()
    {
        for (Tweet tweet : tweets)
        {
            if(listTweet.equals(tweet.getTweet()))
                return tweet;
        }

        return null;
    }
}