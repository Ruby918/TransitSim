/* loic */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import transit.Card;

public class SuccessWarningScreenController extends UiController {

  @FXML
  private Button returnMsgButton;

  @FXML
  private Label SuccessMsg;

  @FXML
  protected void initialize() {

    Card card = (Card) dataStore.get("currentCard").data();

    EditCardScreenController loadScreen = new EditCardScreenController();
    String textLine = SuccessMsg.getText();
    String[] cut = textLine.split("\\s+");
    cut[3] = card.getBalanceString();
    String updatedText="";
    for (int i=0; i<cut.length; i++) {
      updatedText += cut[i] + " ";
    }

    SuccessMsg.setText(updatedText);
  }

  @FXML
  protected void handleReturnMsgButtonAction(ActionEvent event) {
    loadTemplate(HOMEPAGE_SCREEN, returnMsgButton);
  }
}
