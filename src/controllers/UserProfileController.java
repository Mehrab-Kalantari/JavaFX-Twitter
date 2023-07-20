package controllers;

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
import sample.UserDatas;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable
{
    public static UserDatas myUser;

    @FXML
    private Label NameLable, bioLBL;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        bioLBL.setText(myUser.getBio());
        NameLable.setText(myUser.getUsername());
    }

    @FXML
    private void backAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("Following");
    }

    @FXML
    private void TweetsAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("userTweets");
    }
}
