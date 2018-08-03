package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NavController extends UiController {

  @FXML
  protected Button loginButton;

  @FXML
  protected Button logoutButton;

  @FXML
  protected Button adminButton;

  @FXML
  protected Label loggedInLabel;

  @FXML
  public void initialize() {
    if (user != null) {
      loggedInLabel.setText("Logged in as " + user.getName());
      logoutButton.setVisible(true);
      loginButton.setVisible(false);
      if (user.isAdmin) adminButton.setVisible(true);
      else adminButton.setVisible(false);
    }
    else {
      loggedInLabel.setText("");
      logoutButton.setVisible(false);
      loginButton.setVisible(true);
      adminButton.setVisible(false);
    }
  }

  @FXML
  protected void handleLogoutButtonAction(ActionEvent event) {
    Window owner = logoutButton.getScene().getWindow();
    user = null;

    try {
      FXMLLoader titleLoader = new FXMLLoader();
      titleLoader.setLocation(getClass().getResource("template/title_screen.fxml"));
      Scene loginScene = new Scene(titleLoader.load(), 500, 500);
      Stage loginStage = new Stage();
      loginStage.setScene(loginScene);
      owner.hide();
      loginStage.show();
    } catch (IOException e) {
      logger.error("Failed to load title screen from nav.");
    }
  }

  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    Window owner = loginButton.getScene().getWindow();

    try {
      FXMLLoader loginLoader = new FXMLLoader();
      loginLoader.setLocation(getClass().getResource("template/user_login_screen.fxml"));
      Scene loginScene = new Scene(loginLoader.load(), 500, 500);
      Stage loginStage = new Stage();
      loginStage.setScene(loginScene);
      owner.hide();
      loginStage.show();
    } catch (IOException e) {
      logger.error("Failed to load login screen from nav.");
    }
  }

  @FXML
  protected void handleAdminButtonAction(ActionEvent event) {
    Window owner = adminButton.getScene().getWindow();

    try {
      FXMLLoader adminLoader = new FXMLLoader();
      adminLoader.setLocation(getClass().getResource("template/admin_screen.fxml"));
      Scene loginScene = new Scene(adminLoader.load(), 500, 500);
      Stage loginStage = new Stage();
      loginStage.setScene(loginScene);
      owner.hide();
      loginStage.show();
    } catch (IOException e) {
      logger.error("Failed to load admin screen from nav.");
    }
  }
}
