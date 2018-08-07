/* loic */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessCreatedScreenController extends UiController {

  @FXML
  private Button returnMsgButton;

  @FXML
  protected void handleReturnMsgButtonAction(ActionEvent event) {
    loadTemplate(HOMEPAGE_SCREEN, returnMsgButton);
  }
}
