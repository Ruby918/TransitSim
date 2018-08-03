/* the create card controller */

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

public class CreateCardScreenController extends UiController {
  @FXML
  private Button returnButton;

  @FXML
  private Button createButton;

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    Window owner = returnButton.getScene().getWindow();
    owner.hide();
  }

  @FXML
  protected void handleCreateCardButton(ActionEvent event) {
    // Make a message appear on current stage TO DO (maybe)
    // Create the card TO DO BACKEND. done
    api.createCard(UiController.user);
    Window owner = returnButton.getScene().getWindow();
    owner.hide();
  }
}
