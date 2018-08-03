/* loic */

package ui;

import api.LoginFailedException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class UserLoginScreenController extends UiController {
  @FXML
  private Button loginButton;

  @FXML
  private Button returnButton;

  @FXML
  private TextField userField;

  @FXML
  private PasswordField passField;


  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    String email = userField.getText();
    String password = passField.getText();
    try {
      user = api.loginCustomer(email, password);
    } catch (LoginFailedException e) {
      if (email.isEmpty()) {
        email = "<blank>";
      }
      logger.log.warning("Login failed with email " + email);
    }
    loadTemplate(loginButton, "template/user_screen.fxml");
  }

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    loadTemplate(returnButton, "template/title_screen.fxml");
  }
}
