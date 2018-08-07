/*loic*/

package ui;

import api.UpdateUserException;
import javafx.scene.control.TextField;
import transit.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.util.ArrayList;
import transit.PriceModifier;
import transit.Station;
import util.FormattedDate;
import transit.UserAccount;

public class UserScreenController extends UiController {

  private UserAccount user;
  private Card card;
  private Station station;
  private PriceModifier priceModifier;

  @FXML
  private Label homepageLabel;

  @FXML
  private TextField nameField;

  @FXML
  private Button createCardButton;

  @FXML
  private Label balanceAmountLabel;

  @FXML
  private ComboBox selectCardCombo;

  @FXML
  private TextField dateField;

  @FXML
  private TextField timeField;

  @FXML
  protected void initialize() {

    // get current user
    user = (UserAccount) dataStore.get("currentUser").data();
    updateHomepageLabel();
    nameField.setText(user.getName());

    // get current station
    station = (Station) dataStore.get("currentStation").data();

    // get current card
    card = (Card) dataStore.get("currentCard").data();
    if (card != null) {
      updateBalanceLabel();
    }

    // get current priceModifier
    priceModifier = (PriceModifier) dataStore.get("currentPriceModifier").data();

    // set default date and time to now
    FormattedDate date = new FormattedDate();
    dateField.setText(date.toDateString());
    timeField.setText(date.toTimeString());

    // Display all cards user owns
    ArrayList<Card> listOfCards = user.getCards();
    for (int i=0; i<listOfCards.size(); i++) {
      selectCardCombo.getItems().addAll(listOfCards.get(i).getCardId()
          + " - " + listOfCards.get(i).getNickname());
    }

    selectCardCombo.valueProperty().addListener((obs, oldVal, newVal) -> handleCardSelect());
  }

  private void updateHomepageLabel() {
    homepageLabel.setText(user.getName() + "'s Homepage");
  }

  private void updateBalanceLabel() {
    if (card != null) balanceAmountLabel.setText(Double.toString(card.getBalance()));
  }

  private void handleCardSelect() {
    String[] selectedItem = selectCardCombo.getSelectionModel().getSelectedItem().toString().split(" \\- ");
    card = user.getCard(Integer.parseInt(selectedItem[0]));
    if (card != null) {
      dataStore.set("currentCard", new UiData<>(card));
      updateBalanceLabel();
    }
  }

  @FXML
  protected void handleUpdateNameButton(ActionEvent event) {
    try {
      api.user.updateName(user.getEmail(), nameField.getText());
      updateHomepageLabel();
    } catch (UpdateUserException e){}
  }

  @FXML
  protected void handleCreateCardButton(ActionEvent event) {
    loadTemplate("template/create_card_screen.fxml", createCardButton);
  }

  @FXML
  protected void handleLoadCardButton(ActionEvent event) {

    // TODO actually load card
    loadTemplate("template/load_card_screen.fxml", createCardButton);
  }

  @FXML
  protected void handleModCardButton(ActionEvent event) {
    //backEnd
    api.card.addPriceModifier(card, priceModifier);
  }

  @FXML
  protected void handleTapInCardButton(ActionEvent event) {
    //backEnd
    api.tapIn(station, card, dateField.getText(), timeField.getText());
  }

  @FXML
  protected void handleTapOutCardButton(ActionEvent event) {
    //backEnd
    api.tapOut(station, card, dateField.getText(), timeField.getText());
  }
}
