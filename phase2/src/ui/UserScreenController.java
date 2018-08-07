/*loic*/

package ui;

import api.UpdateUserException;
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

  private HashMap<String, Station> stations = new HashMap<>();
  private HashMap<String, Card> cards = new HashMap<>();

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
  private ComboBox selectStationCombo;

  @FXML
  private TextField dateField;

  @FXML
  private TextField timeField;

  @FXML
  private NavigationController navigationController;

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
    for (Card card : listOfCards) {
      cards.put(card.getNickname(), card);
      selectCardCombo.getItems().add(card.getNickname());
    }
    selectCardCombo.valueProperty().addListener((obs, oldVal, newVal) -> handleCardSelect());

    // Display all stations
    ArrayList<Station> stationsList = api.getStations();
    for (Station station : stationsList) {
      String stationString = station + " (" + station.getRoute().toStringSimple() + ")";
      stations.put(stationString, station);
      selectStationCombo.getItems().add(stationString);
    }
    selectStationCombo.valueProperty().addListener((obs, oldVal, newVal) -> handleStationSelect());
  }

  private void updateHomepageLabel() {
    if (user != null) {
      homepageLabel.setText(user.getName() + "'s Homepage");
      navigationController.updateLoggedInText(user.getName());
    }
  }

  private void updateBalanceLabel() {
    if (card != null) balanceAmountLabel.setText(api.card.getBalanceString(card));
  }

  private void handleCardSelect() {
    Card newCard = cards.get(selectCardCombo.getSelectionModel().getSelectedItem().toString());
    if (newCard != null) {
      card = newCard;
      dataStore.set("currentCard", new UiData<>(card));
      updateBalanceLabel();
    }
  }

  private void handleStationSelect() {
    Station newStation = stations.get(selectStationCombo.getSelectionModel().getSelectedItem().toString());
    if (newStation != null) {
      station = newStation;
      dataStore.set("currentStation", new UiData<>(station));
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
    loadTemplate(CREATE_CARD_SCREEN, createCardButton);
  }

  @FXML
  protected void handleLoadCardButton(ActionEvent event) {

    // TODO actually load card
    loadTemplate(LOAD_CARD_SCREEN, createCardButton);
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
    updateBalanceLabel();
  }

  @FXML
  protected void handleTapOutCardButton(ActionEvent event) {
    //backEnd
    api.tapOut(station, card, dateField.getText(), timeField.getText());
    updateBalanceLabel();
  }
}
