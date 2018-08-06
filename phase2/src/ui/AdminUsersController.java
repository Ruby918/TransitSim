package ui;

import api.CreateUserException;
import api.UserForTableView;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import transit.UserAccount;

public class AdminUsersController extends UiController {

  private UserForTableView selectedUser;

  @FXML
  private TableView<UserForTableView> tableViewUsers;

  @FXML
  private TableColumn<UserForTableView, String> Name;

  @FXML
  private TableColumn<UserForTableView, String> Email;

  @FXML
  private TableColumn<UserForTableView, Boolean> IsAdmin;

  @FXML
  private TextField nameField;

  @FXML
  private TextField emailField;

  @FXML
  private CheckBox isAdminCheckBox;

  @FXML
  private Label errorMessage;

  @FXML
  public void handleUserSelect() {
    UserForTableView user = tableViewUsers.getSelectionModel().getSelectedItem();
    if (user != null) {
      selectedUser = user;
      nameField.setText(user.getName());
      emailField.setText(user.getEmail());
      isAdminCheckBox.setSelected(user.getIsAdmin());
    }
  }

  @FXML
  public void handleCreateButton() {
    try {
      UserAccount user = api.user
          .create(nameField.getText(), emailField.getText(), isAdminCheckBox.isSelected());
      logger.log.fine("Created user " + user.toString());
      updateView();
    } catch (CreateUserException e) {
      errorMessage.setText("Failed to create user. All fields are required. Emails must be unique.");
    }
  }

  @FXML
  public void handleDeleteButton() {
    api.user.delete(selectedUser.getEmail());
    updateView();
  }

  @FXML
  public void handleSaveButton() {
    api.user.update(selectedUser.getEmail(), nameField.getText(), emailField.getText(), isAdminCheckBox.isSelected());
    updateView();
  }

  public void initialize(){

    tableViewUsers.getSelectionModel().selectedIndexProperty().addListener((num) -> handleUserSelect());

    Name.setCellValueFactory(new PropertyValueFactory<UserForTableView, String>("name"));
    Email.setCellValueFactory(new PropertyValueFactory<UserForTableView, String>("email"));
    IsAdmin.setCellValueFactory(new PropertyValueFactory<UserForTableView, Boolean>("isAdmin"));

    updateView();
  }

  private void updateView() {
    errorMessage.setText("");
    tableViewUsers.getItems().setAll(api.user.get());
  }


}
