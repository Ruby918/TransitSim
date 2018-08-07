package ui;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import util.FormattedDate;

public class AdminStatisticsController extends UiController{

    @FXML
    private TextField dateField;

    @FXML
    public void initialize() {
        // set default date and time to now
        FormattedDate date = new FormattedDate();
        dateField.setText(date.toDateString());
    }

    @FXML
    public void handleApplyButtonAction() {

    }

    @FXML
    public void handleClearButtonAction() {

    }
}
