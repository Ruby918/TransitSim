/*loic*/

package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import api.Api;

public class UserScreenController extends UiController {

  @FXML
  private Button createCard;

  @FXML
  private Button loadCard;

  @FXML
  private Label balanceLabel;

  @FXML
  public void initialize() {
    double moneyInCard = Api.getMoney(card);
    String textLine = balanceLabel.getText();
    String[] cut = textLine.split("\\s+");
    cut[2] = Double.toString(moneyInCard);
    String updatedText="";
    for (int i=0; i<cut.length; i++) {
      updatedText += cut[i] + " ";
    }

    balanceLabel.setText(updatedText);
  }

  @FXML
  protected void handleCreateCardButton(ActionEvent event) {
    Window owner = createCard.getScene().getWindow();
    try {
      FXMLLoader createLoader = new FXMLLoader();
      createLoader.setLocation(getClass().getResource("template/create_card_screen.fxml"));
      Scene createScene = new Scene(createLoader.load(), 300, 200);
      Stage createStage = new Stage();
      createStage.setScene(createScene);
      createStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void handleLoadCardButton(ActionEvent event) {
    try {
      FXMLLoader loadLoader = new FXMLLoader();
      loadLoader.setLocation(getClass().getResource("template/load_card_screen.fxml"));
      Scene loadScene = new Scene(loadLoader.load(), 300, 200);
      Stage loadStage = new Stage();
      loadStage.setScene(loadScene);
      loadStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void handleModCardButton(ActionEvent event) {

  }

  @FXML
  protected void handleTapInCardButton(ActionEvent event) {

  }

  @FXML
  protected void handleTapOutCardButton(ActionEvent event) {

  }
}
