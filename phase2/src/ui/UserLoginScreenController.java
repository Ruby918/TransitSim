/* loic */

package ui;

import api.LoginFailedException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import transit.CustomerAccount;

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
  private NavController navController;

  @FXML
  public void initialize() {
    navController.loginButton.setVisible(false);
  }


  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    Window owner = loginButton.getScene().getWindow();
    String email = userField.getText();
    String password = passField.getText();
    try {
      user = api.loginCustomer(email, password);
      System.out.println(user);
    } catch (LoginFailedException e) {
      if (email.isEmpty()) {
        email = "<blank>";
      }
      logger.error("Login failed with email " + email);
    }

    try {
      FXMLLoader loginLoader = new FXMLLoader();
      loginLoader.setLocation(getClass().getResource("template/user_screen.fxml"));
      Scene loginScene = new Scene(loginLoader.load(), 500, 500);
      Stage loginStage = new Stage();
      loginStage.setScene(loginScene);
      owner.hide();
      loginStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    Window owner = returnButton.getScene().getWindow();
    try {
      FXMLLoader returnLoader = new FXMLLoader();
      returnLoader.setLocation(getClass().getResource("template/title_screen.fxml"));
      Scene returnScene = new Scene(returnLoader.load(), 270, 150);
      Stage returnStage = new Stage();
      returnStage.setScene(returnScene);
      owner.hide();
      returnStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
