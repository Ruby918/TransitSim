/* loic */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import transit.Card;

public class EditCardScreenController extends UiController {

  private Card card;

  @FXML
  public Button addFifty;

  @FXML
  private Button returnButton;

  @FXML
  private Label balanceField;

  @FXML
  private Label nameLabel;

  @FXML
  private TextField nameField;

  @FXML
  private CheckBox isActiveCheckBox;

  public void initialize() {
    card = (Card) dataStore.get("currentCard").data();
    balanceField.setText(card.getBalanceString());
    nameLabel.setText(card.getNickname());
    nameField.setText(card.getNickname());
    isActiveCheckBox.setSelected(card.isActive());
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

  @FXML
  protected void handleSaveButtonAction() {
    api.card.update(card, nameField.getText(), isActiveCheckBox.isSelected());
    initialize();
  }

  private void load(double amount) {
    api.card.load(card, amount);
    balanceField.setText(card.getBalanceString());
  }
}
