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
    Window owner = createCard.getScene().getWindow();
    try {
      FXMLLoader createLoader = new FXMLLoader();
      createLoader.setLocation(getClass().getResource("template/create_card_screen.fxml"));
      Scene createScene = new Scene(createLoader.load(), 300, 250);
      Stage createStage = new Stage();
      createStage.setScene(createScene);
      createStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void handleUserReturnButtonAction(ActionEvent event) {
    Window owner = userReturn.getScene().getWindow();
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

  @FXML
  protected void handleLoadCardButton(ActionEvent event) {
    try {
      FXMLLoader loadLoader = new FXMLLoader();
      loadLoader.setLocation(getClass().getResource("template/load_card_screen.fxml"));
      Scene loadScene = new Scene(loadLoader.load(), 450, 200);
      Stage loadStage = new Stage();
      loadStage.setScene(loadScene);
      loadStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
