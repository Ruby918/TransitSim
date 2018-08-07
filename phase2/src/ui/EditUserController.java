package ui;

import api.UpdateUserException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import transit.UserAccount;

public class EditUserController extends UiController {

  private UserAccount user;

  @FXML
  private TextField nameField;

  @FXML
  private TextField passwordField;

  @FXML
  private Label editUserLabel;

  public void initialize() {
    user = (UserAccount) dataStore.get("currentUser").data();
    updateHomepageLabel();
    nameField.setText(user.getName());
    passwordField.setText(user.getPassword());
  }

  @FXML
  protected void handleUpdateNameButton(ActionEvent event) {
    String name = nameField.getText();
    try {
      api.user.updateName(user.getEmail(), name);
      updateHomepageLabel();
    } catch (UpdateUserException e){
      logger.log.warning("Failed to update name.");
    }
    logger.log.fine("Successfully updated name to " + name);
  }

  @FXML
  protected void handleUpdatePasswordButton(ActionEvent event) {
    String password = passwordField.getText();
    try {
      api.user.updatePassword(user.getEmail(), password);
    } catch (UpdateUserException e){
      logger.log.warning("Failed to update password.");
    }
    logger.log.fine("Successfully updated password to " + password);
  }

  private void updateHomepageLabel() {
    if (user != null) {
      editUserLabel.setText("User: " + user.getName());
    }
  }
}
