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

    Id.setCellValueFactory(new PropertyValueFactory<>("id"));
    Name.setCellValueFactory(new PropertyValueFactory<>("name"));
    Email.setCellValueFactory(new PropertyValueFactory<>("email"));
    IsAdmin.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

    tableView.getItems().setAll(api.getUsersForTableView());
  }

}
