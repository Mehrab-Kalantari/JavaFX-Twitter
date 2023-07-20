package controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class FollowingController implements Initializable
{
    private String listName;
    private UserDatas followedUser;

    @FXML
    private JFXTextField searchField;

    @FXML
    private Label errorLBL;

    @FXML
    private JFXListView list;

    @FXML
    private Button unfollowBTN, profileBTN;

    private void printList() throws Exception
    {
        list.setItems(FXCollections.observableArrayList(LoginController.loginUser.getFollowing()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            printList();
        }

        catch (Exception e)
        {
        }
    }

    @FXML
    private void listClicked(MouseEvent mouseEvent)
    {
        try
        {
            listName = list.getSelectionModel().getSelectedItem().toString();

            for (UserDatas userData : Main.userDatas)
            {
                if(listName.equals(userData.getUsername()))
                {
                    followedUser = userData;
                }
            }

            unfollowBTN.setDisable(false);
            profileBTN.setDisable(false);
        }
        catch (Exception e)
        {
        }
    }

    @FXML
    private void menuAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("MainMenu");
    }

    @FXML
    private void followAction(ActionEvent actionEvent)
    {
        boolean flag = true;

        String name = searchField.getText();

        if(LoginController.loginUser.getFollowing().contains(name))
        {
            errorLBL.setText("already followed");
        }

        else if(LoginController.loginUser.getUsername().equals(name))
        {
            errorLBL.setText("invalid");
        }

        else
        {
            for (UserDatas userData : Main.userDatas)
            {
                if (name.equals(userData.getUsername()))
                {
                    flag = false;

                    followedUser = userData;

                    // add to followers
                    followedUser.getFollowers().add(LoginController.loginUser.getUsername());

                    errorLBL.setText("successfully added");

                    // add to following
                    LoginController.loginUser.getFollowing().add(name);

                    try
                    {
                        saveInDataBase();
                        saveInDataBase2();

                        new Main().init();

                        printList();
                    }

                    catch (Exception e)
                    {
                    }
                }
            }

            if (flag)
            {
                errorLBL.setText("invalid username");
            }
        }
    }


    /**
     * this func saves new follow or unfollow
     * @throws Exception
     */
    private void saveInDataBase2() throws Exception
    {
        String query = "update user set Followers='%s' where userName='%s'";

        String names = "";

        for (String s : followedUser.getFollowers())
        {
            names += s + "<flag>";
        }


        query = String.format(query, names, followedUser.getUsername());

        DataBaseController.openDataBase(query);
    }


    /**
     * this func saves loginUser following
     * @throws Exception
     */
    private void saveInDataBase() throws Exception
    {
        String query = "update user set Following='%s' where userName='%s'";

        String names = "";

        for (String s : LoginController.loginUser.getFollowing())
        {
            names += s + "<flag>";
        }


        query = String.format(query, names, LoginController.loginUser.getUsername());

        DataBaseController.openDataBase(query);
    }

    @FXML
    private void unfollowAction(ActionEvent actionEvent) throws Exception
    {
        LoginController.loginUser.getFollowing().remove(listName);
        followedUser.getFollowers().remove(LoginController.loginUser.getUsername());

        saveInDataBase();
        saveInDataBase2();

        new Main().init();

        printList();
    }

    @FXML
    private void profileAction(ActionEvent actionEvent) throws Exception
    {
        // passing user to profile class
        UserProfileController.myUser = followedUser;

        PagesController.closePage(actionEvent);
        PagesController.openPage("userProfiles");
    }
}
