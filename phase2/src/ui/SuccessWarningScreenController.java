/* loic */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;
import javafx.scene.control.Label;

public class SuccessWarningScreenController extends UiController {

  @FXML
  private Button returnMsgButton;

  @FXML
  private Label SuccessMsg;

  @FXML
  protected void initialize() {
    LoadCardScreenController loadScreen = new LoadCardScreenController();
    String textLine = SuccessMsg.getText();
    String[] cut = textLine.split("\\s+");
    cut[3] = loadScreen.getMoneyCount();
    String updatedText="";
    for (int i=0; i<cut.length; i++) {
      updatedText += cut[i] + " ";
    }

    SuccessMsg.setText(updatedText);
  }

  @FXML
  protected void handleReturnMsgButtonAction(ActionEvent event) {
    Window owner = returnMsgButton.getScene().getWindow();
    owner.hide();
  }
}
