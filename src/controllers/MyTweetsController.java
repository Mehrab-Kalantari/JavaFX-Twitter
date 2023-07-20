package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Tweet;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyTweetsController implements Initializable
{
    @FXML
    private JFXListView list;

    private String listTweet;

    @FXML
    private JFXButton propBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ArrayList<String> tweets = new ArrayList<>();
        for (Tweet userTweet : LoginController.loginUser.getUserTweets())
        {
            tweets.add(userTweet.getTweet());
        }

        // set tweets in a list to show
        list.setItems(FXCollections.observableArrayList(tweets));
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

    @FXML
    private void menuAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("MainMenu");
    }

    private Tweet search()
    {
        for (Tweet userTweet : LoginController.loginUser.getUserTweets())
        {
            if(listTweet.equals(userTweet.getTweet()))
                return userTweet;
        }

        return null;
    }
}
