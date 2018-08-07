package ui;

import api.SimpleStation;
import api.SimpleRoute;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import transit.Station;

public class AdminMapController extends UiController {

  @FXML
  private Label stationsLabel;

  @FXML
  private TableView<SimpleStation> tableViewStations;

  @FXML
  private TableView<SimpleRoute> tableViewRoutes;

  @FXML
  private TableColumn<SimpleStation, String> StationName;

  @FXML
  private TableColumn<SimpleStation, String> StationType;

  @FXML
  private TableColumn<SimpleStation, String> StationRoute;

  @FXML
  private TableColumn<SimpleStation, String> Adjacent;

  @FXML
  private TableColumn<SimpleRoute, String> RouteName;

  @FXML
  private TableColumn<SimpleRoute, String> RouteType;

  @FXML
  private EditStationController editStationController;

  public void initialize(){

    tableViewRoutes.getSelectionModel().selectedIndexProperty().addListener((num) -> handleRouteSelect());
    tableViewStations.getSelectionModel().selectedIndexProperty().addListener((num) -> handleStationSelect());

    StationName.setCellValueFactory(new PropertyValueFactory<SimpleStation, String>("name"));
    StationType.setCellValueFactory(new PropertyValueFactory<SimpleStation, String>("type"));
    StationRoute.setCellValueFactory(new PropertyValueFactory<SimpleStation, String>("route"));
    Adjacent.setCellValueFactory(new PropertyValueFactory<SimpleStation, String>("adjacent"));
    tableViewStations.getItems().setAll(api.map.getStationsSimple());

    RouteName.setCellValueFactory(new PropertyValueFactory<SimpleRoute, String>("name"));
    RouteType.setCellValueFactory(new PropertyValueFactory<SimpleRoute, String>("type"));
    tableViewRoutes.getItems().setAll(api.map.getRoutesSimple());
  }

  @FXML
  public void handleRouteSelect() {
    SimpleRoute route = tableViewRoutes.getSelectionModel().getSelectedItem();
    if (route != null) {
      stationsLabel.setText("Stations (" + route.getName() + " " + route.getType() + ")");
      tableViewStations.getItems().clear();
      tableViewStations.getItems().setAll(api.map.getStationsSimple(route));
    }
  }

  @FXML
  public void handleStationSelect() {
    SimpleStation simpleStation = tableViewStations.getSelectionModel().getSelectedItem();
    if (simpleStation != null) {
      Station station = api.map.getStation(simpleStation);
      dataStore.set(UiDataStore.CURRENT_STATION, new UiData<>(station));
      editStationController.initialize();
    }
  }

  @FXML
  public void handleClearButton() {
    stationsLabel.setText("Stations");
    tableViewRoutes.getSelectionModel().clearSelection();
    tableViewStations.getItems().setAll(api.map.getStationsSimple());
  }
}
