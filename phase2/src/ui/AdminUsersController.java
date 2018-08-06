package ui;

import api.UserForTableView;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import transit.UserAccount;

public class AdminUsersController extends UiController {

  @FXML
  private TableView<UserForTableView> tableViewUsers;

  @FXML
  private TableColumn<UserForTableView, Integer> Id;

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
  public void handleUserSelect() {
    UserForTableView user = tableViewUsers.getSelectionModel().getSelectedItem();
    nameField.setText(user.getName());
    emailField.setText(user.getEmail());
    isAdminCheckBox.setSelected(user.getIsAdmin());
  }

  @FXML
  public void handleCreateButton() {
    UserAccount user =  api.user.create(nameField.getText(), emailField.getText(), isAdminCheckBox.isSelected());
    logger.log.fine("Created user " + user.toString());
    updateView();
  }

  @FXML
  public void handleDeleteButton() {
    
  }

  @FXML
  public void handleSaveButton() {

  }

  public void initialize(){

    tableViewUsers.getSelectionModel().selectedIndexProperty().addListener((num) -> handleUserSelect());

    Id.setCellValueFactory(new PropertyValueFactory<UserForTableView, Integer>("id"));
    Name.setCellValueFactory(new PropertyValueFactory<UserForTableView, String>("name"));
    Email.setCellValueFactory(new PropertyValueFactory<UserForTableView, String>("email"));
    IsAdmin.setCellValueFactory(new PropertyValueFactory<UserForTableView, Boolean>("isAdmin"));

    updateView();
  }

  private void updateView() {
    tableViewUsers.getItems().setAll(api.getUsers());

  }


}
