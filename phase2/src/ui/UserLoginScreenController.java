/* loic */

package ui;

import api.LoginFailedException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
  protected void handleLoginButtonAction(ActionEvent event) {
    String email = userField.getText();
    String password = passField.getText();

    if (email.isEmpty()) {
      email = "<blank>";
    }

    try {
      UserAccount user = api.user.login(email, password);
      dataStore.set("currentUser", new UiData<UserAccount>(user));
      loadTemplate("template/user_screen.fxml", loginButton);
    } catch (LoginFailedException e) {
      // TODO display something in the UI
    }
  }

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    loadTemplate("template/title_screen.fxml", returnButton);
  }
}
