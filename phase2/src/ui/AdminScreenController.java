/* loic */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminScreenController extends UiController {
  @FXML
  private Button adminReturn;

  @FXML
  protected void handleAdminReturnButtonAction(ActionEvent event) {
    loadTemplate("template/title_screen.fxml", adminReturn);
  }
}
