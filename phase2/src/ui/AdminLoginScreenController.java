/* the login screen for an admin */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminLoginScreenController extends UiController {

  @FXML
  private Button loginButton;

  @FXML
  private Button returnButton;

  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    loadTemplate(loginButton,"template/admin_screen.fxml");
  }

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    loadTemplate(returnButton, "template/title_screen.fxml");
  }
}
