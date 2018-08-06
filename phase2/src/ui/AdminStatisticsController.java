package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminStatisticsController extends UiController{
    @FXML
    private Button confirm;

    @FXML
    private TextField dateField;
    @FXML
    protected void handleConfirmButtonAction(ActionEvent event) {
        System.out.println(dateField.getText());
        loadTemplate("template/show_stats.fxml", confirm);
    }


}
