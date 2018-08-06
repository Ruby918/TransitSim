/* the create card controller */

package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import transit.UserAccount;

public class CreateCardScreenController extends UiController {
  @FXML
  private Button returnButton;

  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    Window owner = returnButton.getScene().getWindow();
    owner.hide();
  }

  @FXML
  protected void handleCreateCardButton(ActionEvent event) {

    // get current user
    UiData userData = dataStore.get("currentUser");
    if (userData != null) {
      UserAccount user = (UserAccount) userData.data();
      api.createCard(user);
    } else {
      logger.log.warning("Can't create card on null user.");
    }

    Window owner = returnButton.getScene().getWindow();
    owner.hide();
    try {
      FXMLLoader moneyLoader = new FXMLLoader();
      moneyLoader.setLocation(getClass().getResource("template/success_create_screen.fxml"));
      Scene moneyScene = new Scene(moneyLoader.load(), 350, 150);
      Stage moneyStage = new Stage();
      moneyStage.setScene(moneyScene);
      moneyStage.show();
      owner.hide();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
