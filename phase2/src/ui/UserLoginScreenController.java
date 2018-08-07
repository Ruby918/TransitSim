/* loic */

package ui;

import api.LoginFailedException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import transit.UserAccount;

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
  private Label errorMessage;

  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    String email = userField.getText();
    String password = passField.getText();

    errorMessage.setText("");

    if (email.isEmpty()) {
      email = "<blank>";
    }

    try {
      UserAccount user = api.user.login(email, password);
      dataStore.set("currentUser", new UiData<UserAccount>(user));
      loadTemplate(UiController.HOMEPAGE_SCREEN, loginButton);
    } catch (LoginFailedException e) {
      errorMessage.setText("Log in failed.");
    }
  }

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    loadTemplate(UiController.TITLE_SCREEN, returnButton);
  }
}
