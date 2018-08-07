/*loic*/

package ui;

import java.util.HashMap;
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
  private Button createCardButton;

  @FXML
  private Label balanceAmountLabel;

  @FXML
  private ComboBox<Card> selectCardCombo;

  @FXML
  private ComboBox<Station> selectStationCombo;

  @FXML
  private TextField dateField;

  @FXML
  private TextField timeField;

  @FXML
  private Label errorMessage;

  @FXML
  private Label successMessage;

  @FXML
  private Label infoMessage;

  @FXML
  protected void initialize() {

    // reset error message
    errorMessage.setText("");

    // get current user
    user = (UserAccount) dataStore.get("currentUser").data();

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
    selectCardCombo.getItems().addAll(user.getCards());
    selectCardCombo.valueProperty().addListener((obs, oldVal, newVal) -> handleCardSelect());

    // Display all stations
    selectStationCombo.getItems().addAll(api.map.getStations());
    selectStationCombo.valueProperty().addListener((obs, oldVal, newVal) -> handleStationSelect());
  }

  private void updateBalanceLabel() {
    clearMessages();
    if (card != null) balanceAmountLabel.setText(api.card.getBalanceString(card));
  }

  private void handleCardSelect() {
    clearMessages();
    Card newCard = selectCardCombo.getSelectionModel().getSelectedItem();
    if (newCard != null) {
      card = newCard;
      dataStore.set("currentCard", new UiData<>(card));
      updateBalanceLabel();
    }
  }

  private void handleStationSelect() {
    clearMessages();
    Station newStation = selectStationCombo.getSelectionModel().getSelectedItem();
    if (newStation != null) {
      station = newStation;
      dataStore.set("currentStation", new UiData<>(station));
    }
  }

  @FXML
  protected void handleCreateCardButton(ActionEvent event) {
    loadTemplate(CREATE_CARD_SCREEN, createCardButton);
  }

  @FXML
  protected void handleLoadCardButton(ActionEvent event) {
    loadTemplate(LOAD_CARD_SCREEN, createCardButton);
  }

  @FXML
  protected void handleModCardButton(ActionEvent event) {
    //backEnd
    api.card.addPriceModifier(card, priceModifier);
  }

  @FXML
  protected void handleTapInCardButton(ActionEvent event) {
    try {
      api.tapIn(station, card, dateField.getText(), timeField.getText());
      updateBalanceLabel();
      setSuccessMessage();
    } catch (Exception e) {
      setErrorMessage();
    }
  }

  @FXML
  protected void handleTapOutCardButton(ActionEvent event) {
    try {
      api.tapOut(station, card, dateField.getText(), timeField.getText());
      updateBalanceLabel();
      setSuccessMessage();
    } catch (Exception e) {
      setErrorMessage();
    }
  }

  private void clearMessages() {
    errorMessage.setText("");
    successMessage.setText("");
    infoMessage.setText("");
  }

  private void setSuccessMessage() {
    clearMessages();
    successMessage.setText("Success.");
  }

  private void setErrorMessage() {
    clearMessages();
    errorMessage.setText("Tap failed.");
  }

  private void setInfoMessage() {
    clearMessages();
    infoMessage.setText("Nothing to tap.");
  }
}
