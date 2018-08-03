/*loic*/

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserScreenController extends UiController {
  @FXML
  private Button userReturn;

  @FXML
  protected void handleCreateCardButton(ActionEvent event) {
    loadTemplate("template/create_card_screen.fxml");
  }

  @FXML
  protected void handleUserReturnButtonAction(ActionEvent event) {
    loadTemplate(userReturn, "template/title_screen.fxml");
  }

  @FXML
  protected void handleLoadCardButton(ActionEvent event) {
    loadTemplate("template/load_card_screen.fxml");
  }
}
