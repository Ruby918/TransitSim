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

public class AdminScreenController extends UiController {
  @FXML
  private Button adminReturn;

  @FXML
  protected void handleAdminReturnButtonAction(ActionEvent event) {
    loadTemplate(adminReturn, "template/title_screen.fxml");
  }
}
