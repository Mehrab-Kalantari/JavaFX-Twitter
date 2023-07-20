package controllers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomingController implements Initializable
{
    @FXML
    private AnchorPane welcomingMenu;

    /**
     *  this func shows splash screen just one time
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(SplashScreenController.isFinished)
        {
            try
            {
                splashScreenShow();
            }
            catch (Exception e)
            {
            }
        }
    }

    @FXML
    private void exitAction(ActionEvent actionEvent)
    {
        System.exit(0);
    }

    @FXML
    private void startAction(ActionEvent actionEvent) throws Exception
    {
        PagesController.closePage(actionEvent);
        PagesController.openPage("Login");
    }

    private void splashScreenShow() throws Exception
    {
        /**
         * show this page 1 time
         */
        SplashScreenController.isFinished = false;

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../pages/splash screen.fxml"));
        welcomingMenu.getChildren().setAll(anchorPane);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(4), anchorPane);

        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(4), anchorPane);

        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        fadeIn.setOnFinished((event ->
        {
            fadeOut.play();
        }));


        fadeOut.setOnFinished((event ->
        {
            try
            {
                AnchorPane anchorPane2 = FXMLLoader.load(getClass().getResource("../pages/welcoming menu.fxml"));
                welcomingMenu.getChildren().setAll(anchorPane2);
            }
            catch (IOException e)
            {
            }

        }));
    }
}
