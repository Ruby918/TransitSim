/* loic */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import transit.Card;

public class LoadCardScreenController extends UiController {

  private Card card;

  @FXML
  public Button addFifty;

  @FXML
  private Button returnButton;

  @FXML
  private Label balanceField;

  @FXML
  private Label nameLabel;

  public void initialize() {
    card = (Card) dataStore.get("currentCard").data();
    balanceField.setText(card.getBalanceString());
    nameLabel.setText(card.getNickname());
  }

  @FXML
  protected void handleAddTenButtonAction(ActionEvent event) {
    load(10);
  }

  @FXML
  protected void handleAddTwentyButtonAction(ActionEvent event) {
    load(20);
  }

  @FXML
  protected void handleAddFiftyButtonAction(ActionEvent event) {
    load(50);
  }


  @FXML
  protected void handleReturnButtonAction(ActionEvent event) {
    loadTemplate(HOMEPAGE_SCREEN, returnButton);
  }

  private void load(double amount) {
    api.card.load(card, amount);
    balanceField.setText(card.getBalanceString());
  }
}
