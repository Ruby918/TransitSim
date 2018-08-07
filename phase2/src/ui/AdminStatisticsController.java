package ui;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import util.FormattedDate;

public class AdminStatisticsController extends UiController{

    @FXML
    private TextField dateField;

    @FXML
    public void initialize() {
        // set date to current filter date
        FormattedDate date = (FormattedDate) dataStore.get("currentFilterDate").data();
        if (date != null) dateField.setText(date.toDateString());
        else dateField.setText("");
    }

    @FXML
    public void handleApplyButtonAction() {
        FormattedDate date = new FormattedDate(dateField.getText());
        dataStore.set("currentFilterDate", new UiData<>(date));
    }

    @FXML
    public void handleClearButtonAction() {
        dataStore.set("currentFilterDate", null);
        dateField.setText("");;
    }
}
