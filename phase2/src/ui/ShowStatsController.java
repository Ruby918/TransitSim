package ui;

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
import javafx.scene.control.TextField;

import javafx.stage.Window;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ShowStatsController extends UiController {

    @FXML
    private Button returnBack;

    @FXML
    private TextField dateInput;

    @FXML
    protected void handleReturnBackButtonAction(ActionEvent event) {
        Window owner = returnBack.getScene().getWindow();
        try {
            FXMLLoader mainLoader = new FXMLLoader();
            mainLoader.setLocation(getClass().getResource("template/admin_screen.fxml"));
            Scene mainScene = new Scene(mainLoader.load(), 500, 500);
            Stage mainStage = new Stage();
            mainStage.setScene(mainScene);
            owner.hide();
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
