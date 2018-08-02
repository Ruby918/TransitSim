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

public class AdminScreenController {
  @FXML
  private Button adminReturn;

  @FXML
  protected void handleAdminReturnButtonAction(ActionEvent event) {
    Window owner = adminReturn.getScene().getWindow();
    try {
      FXMLLoader mainLoader = new FXMLLoader();
      mainLoader.setLocation(getClass().getResource("template/title_screen.fxml"));
      Scene mainScene = new Scene(mainLoader.load(), 270, 150);
      Stage mainStage = new Stage();
      mainStage.setScene(mainScene);
      owner.hide();
      mainStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
