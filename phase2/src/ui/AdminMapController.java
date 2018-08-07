package ui;

import api.SimpleStation;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminMapController extends UiController {

  @FXML
  private TableView<SimpleStation> tableViewStations;

  @FXML
  private TableColumn<SimpleStation, String> Name;

  @FXML
  private TableColumn<SimpleStation, String> Route;

  @FXML
  private TableColumn<SimpleStation, String> Adjacent;

  public void initialize(){

    Name.setCellValueFactory(new PropertyValueFactory<SimpleStation, String>("name"));
    Route.setCellValueFactory(new PropertyValueFactory<SimpleStation, String>("route"));
    Adjacent.setCellValueFactory(new PropertyValueFactory<SimpleStation, String>("adjacent"));

    tableViewStations.getItems().setAll(api.getStationsSimple());
  }
}
