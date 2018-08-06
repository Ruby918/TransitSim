/* loic */

package ui;

import java.io.IOException;

import ui.LoadCardScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class SuccessCreatedScreenController extends UiController {

  @FXML
  private Button returnMsgButton;

  @FXML
  private Label SuccessMsg;

  @FXML
  protected void handleReturnMsgButtonAction(ActionEvent event) {
    Window owner = returnMsgButton.getScene().getWindow();
    try {
      FXMLLoader loginLoader = new FXMLLoader();
      loginLoader.setLocation(getClass().getResource("template/user_screen.fxml"));
      Scene loginScene = new Scene(loginLoader.load(), 350, 400);
      Stage loginStage = new Stage();
      loginStage.setScene(loginScene);
      owner.hide();
      loginStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
