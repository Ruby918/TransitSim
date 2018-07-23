/*loic*/

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

public class UserScreenController {
  @FXML
  private Button userReturn;

  @FXML
  protected void handleUserReturnButtonAction(ActionEvent event) {
    Window owner = userReturn.getScene().getWindow();
    try {
      FXMLLoader mainLoader = new FXMLLoader();
      mainLoader.setLocation(getClass().getResource("title_screen.fxml"));
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
