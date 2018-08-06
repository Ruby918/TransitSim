package ui;

import api.StationForTableView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminMapController extends UiController {

  @FXML
  private TableView<StationForTableView> tableViewStations;

  @FXML
  private TableColumn<StationForTableView, String> Name;

  @FXML
  private TableColumn<StationForTableView, String> Route;

  @FXML
  private TableColumn<StationForTableView, String> Adjacent;

  public void initialize(){

    Name.setCellValueFactory(new PropertyValueFactory<StationForTableView, String>("name"));
    Route.setCellValueFactory(new PropertyValueFactory<StationForTableView, String>("route"));
    Adjacent.setCellValueFactory(new PropertyValueFactory<StationForTableView, String>("adjacent"));

    tableViewStations.getItems().setAll(api.getStations());
  }
}
