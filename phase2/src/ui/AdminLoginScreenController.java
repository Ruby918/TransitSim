/* the login screen for an admin */

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

public class AdminLoginScreenController {

  @FXML
  private Button loginButton;

  @FXML
  private Button returnButton;

  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    Window owner = loginButton.getScene().getWindow();
    try {
      FXMLLoader loginLoader = new FXMLLoader();
      loginLoader.setLocation(getClass().getResource("admin_screen.fxml"));
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
      returnLoader.setLocation(getClass().getResource("title_screen.fxml"));
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
