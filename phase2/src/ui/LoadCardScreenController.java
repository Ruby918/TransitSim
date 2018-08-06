/* loic */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.scene.control.Label;
import transit.Card;

public class LoadCardScreenController extends UiController {

  @FXML
  public Button addFiveTen;

  @FXML
  private Button returnButton;

  @FXML
  private Button addMoneyButton;

  private static String finalMoneyCount;

  @FXML
  private Label moneyCountField;

  public String getMoneyCountField() {
      return finalMoneyCount;
  }

  @FXML
  protected void handleAddFiveButtonAction(ActionEvent event) {
    String moneyCountLabel = moneyCountField.getText();
    double newTotal = Double.parseDouble(moneyCountLabel) + 5.00;
    moneyCountField.setText(Double.toString(newTotal)+"0");
  }

  @FXML
  protected void handleAddTenButtonAction(ActionEvent event) {
    String moneyCountLabel = moneyCountField.getText();
    double newTotal = Double.parseDouble(moneyCountLabel) + 10.00;
    moneyCountField.setText(Double.toString(newTotal)+"0");
  }

  @FXML
  protected void handleAddTwoButtonAction(ActionEvent event) {
    String moneyCountLabel = moneyCountField.getText();
    double newTotal = Double.parseDouble(moneyCountLabel) + 20.00;
    moneyCountField.setText(Double.toString(newTotal)+"0");
  }

  @FXML
  protected void handleAddFiveTenButtonAction(ActionEvent event) {
    String moneyCountLabel = moneyCountField.getText();
    double newTotal = Double.parseDouble(moneyCountLabel) + 50.00;
    moneyCountField.setText(Double.toString(newTotal)+"0");
  }

  @FXML
  protected void handleResetCounterButtonAction(ActionEvent event) {
    moneyCountField.setText(Double.toString(0.00)+"0");
  }

  @FXML
  protected void handleAddMoneyButtonAction(ActionEvent event) {
    finalMoneyCount = moneyCountField.getText();

    Card card = (Card) dataStore.get("currentCard").data();
    if (card != null) {
      loadTemplate("template/success_warning_screen.fxml", addMoneyButton);
      // TODO actually add money
    } else {
      // TODO do something
    }
  }

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    Window owner = returnButton.getScene().getWindow();
    owner.hide();
  }
}
