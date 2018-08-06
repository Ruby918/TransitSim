/* the create card controller */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;
import transit.UserAccount;

public class CreateCardScreenController extends UiController {
  @FXML
  private Button returnButton;

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    loadTemplate("template/user_screen.fxml", returnButton);
  }

  @FXML
  protected void handleCreateCardButton(ActionEvent event) {

    // get current user
    UserAccount user = (UserAccount) dataStore.get("currentUser").data();
    if (user != null) {
      api.createCard(user);
      loadTemplate("template/success_create_screen.fxml", returnButton);
    } else {
      logger.log.warning("Can't create card on null user.");
      // TODO display something
    }
  }
}
