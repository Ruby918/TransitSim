/* loic */

package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class LoadCardScreenController extends UiController {

  @FXML
  private Button addFive;

  @FXML
  private Button addTen;

  @FXML
  private Button addTwo;

  @FXML
  public Button addFiveTen;

  @FXML
  private Button returnButton;

  @FXML
  private Button resetCounter;

  @FXML
  private Button addMoney;

  private static String finalMoneyCount;

  @FXML
  private Label moneyCount;

  public String getMoneyCount() {
    return finalMoneyCount;
  }

  @FXML
  protected void handleAddFiveButtonAction(ActionEvent event) {
    String moneyCountLabel = moneyCount.getText();
    double newTotal = Double.parseDouble(moneyCountLabel) + 5.00;
    moneyCount.setText(Double.toString(newTotal)+"0");
  }

  @FXML
  protected void handleAddTenButtonAction(ActionEvent event) {
    String moneyCountLabel = moneyCount.getText();
    double newTotal = Double.parseDouble(moneyCountLabel) + 10.00;
    moneyCount.setText(Double.toString(newTotal)+"0");
  }

  @FXML
  protected void handleAddTwoButtonAction(ActionEvent event) {
    String moneyCountLabel = moneyCount.getText();
    double newTotal = Double.parseDouble(moneyCountLabel) + 20.00;
    moneyCount.setText(Double.toString(newTotal)+"0");
  }

  @FXML
  protected void handleAddFiveTenButtonAction(ActionEvent event) {
    String moneyCountLabel = moneyCount.getText();
    double newTotal = Double.parseDouble(moneyCountLabel) + 50.00;
    moneyCount.setText(Double.toString(newTotal)+"0");
  }

  @FXML
  protected void handleResetCounterButtonAction(ActionEvent event) {
    moneyCount.setText(Double.toString(0.00)+"0");
  }

  @FXML
  protected void handleAddMoneyButtonAction(ActionEvent event) {
    // Add the money to the system TO DO BACKEND
    Window owner = addMoney.getScene().getWindow();
    finalMoneyCount = moneyCount.getText();
    loadTemplate(addMoney, "template/success_warning_screen.fxml");
  }

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    Window owner = returnButton.getScene().getWindow();
    owner.hide();
  }
}
