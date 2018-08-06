package ui;

import api.UserForTableView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminCustomersController extends UiController {

  @FXML
  private TableView<UserForTableView> tableView;

  @FXML
  private TableColumn<UserForTableView, Integer> Id;

  @FXML
  private TableColumn<UserForTableView, String> Name;

  @FXML
  private TableColumn<UserForTableView, String> Email;

  @FXML
  private TableColumn<UserForTableView, Boolean> IsAdmin;

  public void initialize(){

    Id.setCellValueFactory(new PropertyValueFactory<UserForTableView, Integer>("id"));
    Name.setCellValueFactory(new PropertyValueFactory<UserForTableView, String>("name"));
    Email.setCellValueFactory(new PropertyValueFactory<UserForTableView, String>("email"));
    IsAdmin.setCellValueFactory(new PropertyValueFactory<UserForTableView, Boolean>("isAdmin"));

    tableView.getItems().setAll(api.getUsers());
  }
}
