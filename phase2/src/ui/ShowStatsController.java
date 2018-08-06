package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ShowStatsController extends UiController {

    @FXML
    private Button returnBack;

    @FXML
    protected void handleReturnBackButtonAction(ActionEvent event) {
        loadTemplate("template/admin_screen.fxml", returnBack);
    }
}
