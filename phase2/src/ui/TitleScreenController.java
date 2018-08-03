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

public class TitleScreenController extends UiController {
  @FXML
  private Button userButton;

  @FXML
  private Button adminButton;

  public void initialize() {
  }

  @FXML
  protected void handleUserButtonAction(ActionEvent event) {
    Window owner = userButton.getScene().getWindow();
    try {
      FXMLLoader userLoader = new FXMLLoader();
      userLoader.setLocation(getClass().getResource("template/user_login_screen.fxml"));
      Scene userScene = new Scene(userLoader.load(), 350, 250);
      Stage userStage = new Stage();
      userStage.setScene(userScene);
      owner.hide();
      userStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void handleAdminButtonAction(ActionEvent event) {
    Window owner = adminButton.getScene().getWindow();
    try {
      FXMLLoader adminLoader = new FXMLLoader();
      adminLoader.setLocation(getClass().getResource("template/admin_login_screen.fxml"));
      Scene adminScene = new Scene(adminLoader.load(), 350, 250);
      Stage adminStage = new Stage();
      adminStage.setScene(adminScene);
      owner.hide();
      adminStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
