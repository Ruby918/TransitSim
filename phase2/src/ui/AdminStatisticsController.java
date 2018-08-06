package ui;

import java.io.IOException;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class AdminStatisticsController extends UiController{

    private Button returnButton;
    @FXML

    protected void handleReturnButtonAction(ActionEvent event) {
        Window owner = this.returnButton.getScene().getWindow();
        owner.hide();
    }
    protected void handleCreateCardButton(ActionEvent event) {
        // Make a message appear on current stage TO DO (maybe)
        api.createCard(UiController.user);
        Window owner = returnButton.getScene().getWindow();
        owner.hide();
        try {
            FXMLLoader moneyLoader = new FXMLLoader();
            moneyLoader.setLocation(getClass().getResource("template/success_create_screen.fxml"));
            Scene moneyScene = new Scene(moneyLoader.load(), 350, 150);
            Stage moneyStage = new Stage();
            moneyStage.setScene(moneyScene);
            moneyStage.show();
            owner.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
