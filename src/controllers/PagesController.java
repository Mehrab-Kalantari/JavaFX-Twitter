package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *  this class closes and opens pages
 */

public class PagesController
{
    /**
     * closing stage using this func
     * @param actionEvent
     */
    public static void closePage(ActionEvent actionEvent)
    {
        Stage newStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        newStage.close();
    }

    /**
     * opening page using this func
     * @param name
     * @throws Exception
     */
    public static void openPage(String name) throws Exception
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(PagesController.class.getResource("../pages/" + name + ".fxml"));

        MouseController.handle(root, stage);

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
