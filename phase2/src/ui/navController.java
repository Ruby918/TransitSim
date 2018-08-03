package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class navController extends UiController {

  @FXML
  public Label loggedInLabel;
  @FXML
  public void initialize() {
    if (user != null) {
      loggedInLabel.setText("Logged in as " + user.getName());
    }
  }
}
