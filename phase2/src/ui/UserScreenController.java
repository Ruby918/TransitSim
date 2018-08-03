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

public class UserScreenController extends UiController {
  @FXML
  private Button userReturn;

  @FXML
  private Button createCard;

  @FXML
  private Button loadCard;

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
