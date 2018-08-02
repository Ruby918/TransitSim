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

public class LoadCardScreenController {

  @FXML
  private Button addFive;

  @FXML
  private Button addTen;

  @FXML
  private Button addTwo;

  @FXML
  private Button addFiveTen;

  @FXML
  private Button returnButton;

  @FXML
  protected void handleAddFiveButtonAction(ActionEvent event) {
    // Make the display add 5 TO DO
    // Make the card load $5 TO DO BACKEND
  }

  @FXML
  protected void handleAddTenButtonAction(ActionEvent event) {

  }

  @FXML
  protected void handleAddTwoButtonAction(ActionEvent event) {

  }

  @FXML
  protected void handleAddFiveTenButtonAction(ActionEvent event) {

  }

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    Window owner = returnButton.getScene().getWindow();
    owner.hide();
  }
}
