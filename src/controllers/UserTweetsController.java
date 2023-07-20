package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.Tweet;
import sample.UserDatas;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserTweetsController implements Initializable
{
    private String listTweet;

    @FXML
    private Button likeBTN, propBTN;

    @FXML
    private ListView list;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ArrayList<String> tweets = new ArrayList<>();

        for (Tweet userTweet : UserProfileController.myUser.getUserTweets())
        {
            tweets.add(userTweet.getTweet());
        }

        list.setItems(FXCollections.observableArrayList(tweets));
    }

    @FXML
    private void backAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("userProfiles");
    }

    @FXML
    private void listClicked(MouseEvent mouseEvent)
    {
        try
        {
            listTweet = list.getSelectionModel().getSelectedItem().toString();

            likeBTN.setDisable(false);
            propBTN.setDisable(false);
        }

        catch (Exception e)
        {
        }
    }

    private Tweet search()
    {
        for (Tweet userTweet : UserProfileController.myUser.getUserTweets())
        {
            if(listTweet.equals(userTweet.getTweet()))
                return userTweet;
        }

        return null;
    }

    @FXML
    private void propAction(ActionEvent actionEvent) throws Exception
    {
        TweetPropertiesController.tweet = search();
        PagesController.openPage("tweetProperties");
    }

    @FXML
    private void likeAction(ActionEvent actionEvent)
    {
        for (Tweet userTweet : UserProfileController.myUser.getUserTweets())
        {
            if(listTweet.equals(userTweet.getTweet()))
            {
                if(!userTweet.getNames().contains(LoginController.loginUser.getUsername()))
                {
                    userTweet.like(LoginController.loginUser.getUsername());

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
        }
    }

    private void saveInDataBase() throws Exception
    {
        String query = "update user set Likes='%s' where userName='%s'";

        String likes = "";

        for (Tweet userTweet : UserProfileController.myUser.getUserTweets())
        {
            likes += userTweet.getLikes();

            for (String name : userTweet.getNames())
            {
                likes += "<nameFlag>" + name;
            }

            likes += "<flag>";
        }

        query = String.format(query, likes, UserProfileController.myUser.getUsername());

        DataBaseController.openDataBase(query);
    }
}
