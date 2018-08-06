/*loic*/

package ui;

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
import transit.UserAccount;

public class UserScreenController extends UiController {

  private UserAccount user;
  private Card card;
  private Station station;
  private PriceModifier priceModifier;

  @FXML
  private Button createCardButton;

  @FXML
  private Label balanceLabel;

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
    // get current user
    station = (Station) dataStore.get("currentStation").data();
    // get current user
    priceModifier = (PriceModifier) dataStore.get("currentPriceModifier").data();

    // Update the balance
    String textLine = balanceLabel.getText();
    String updatedText="";
    String[] cut = textLine.split("\\s+");
    if (user.hasCard()) {
      double moneyInCard = api.getMoney(user.getCards().get(0));
      cut[2] = Double.toString(moneyInCard);
      for (int i=0; i<cut.length; i++) {
        updatedText += cut[i] + " ";
      }
    } else {
      updatedText=textLine;
    }
    balanceLabel.setText(updatedText);

    // Display all cards user owns
    ArrayList<Card> listOfCards = user.getCards();
    for (int i=0; i<listOfCards.size(); i++) {
      selectCardCombo.getItems().addAll(listOfCards.get(i).getCardId());
    }

    selectCardCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
      String[] cut2 = balanceLabel.getText().split("\\s+");
      String updatedText2="";
      String id = selectCardCombo.getSelectionModel().getSelectedItem().toString();

      card = user.getCard(Integer.parseInt(id));
      dataStore.set("currentCard", new UiData<Card>(card));

      cut2[2] = Double.toString(api.getMoney(card));
      for (int i=0; i<cut2.length; i++) {
        updatedText2 += cut2[i] + " ";
      }
      balanceLabel.setText(updatedText2);
    });
  }

  @FXML
  protected void handleCreateCardButton(ActionEvent event) {

    //back end
    api.createCard(user);
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
    api.addPriceModifier(card, priceModifier);
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
