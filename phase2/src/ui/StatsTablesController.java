package ui;

import transit.simplemodel.SimpleRoute;
import transit.simplemodel.SimpleStation;
import transit.simplemodel.SimpleUser;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class StatsTablesController extends UiController {

  @FXML
  private TableView<SimpleStation> tableViewStations;

  @FXML
  private TableView<SimpleUser> tableViewUsers;

  @FXML
  private TableView<SimpleRoute> tableViewRoutes;

  @FXML
  public void initialize() {
    tableViewStations.getItems().setAll(api.map.getStationsSimple());
    tableViewUsers.getItems().setAll(api.user.get());
    tableViewRoutes.getItems().setAll(api.map.getRoutesSimple());
  }
}
