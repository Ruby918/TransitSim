package ui;

import transit.simplemodel.SimpleRoute;
import transit.simplemodel.SimpleStation;
import transit.simplemodel.SimpleTap;
import transit.simplemodel.SimpleTransaction;
import transit.simplemodel.SimpleTrip;
import transit.simplemodel.SimpleUser;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class StatsTablesController extends UiController {

  @FXML
  private TableView<SimpleStation> tableViewStations;

  @FXML
  private TableView<SimpleTap> tableViewTaps;

  @FXML
  private TableView<SimpleTransaction> tableViewTransactions;

  @FXML
  private TableView<SimpleTrip> tableViewTrips;

  @FXML
  private TableView<SimpleUser> tableViewUsers;

  @FXML
  private TableView<SimpleRoute> tableViewRoutes;

  @FXML
  public void initialize() {
    tableViewStations.getItems().setAll(api.map.getStationsSimple());
    tableViewTrips.getItems().setAll(api.stats.getTripsSimple());
    tableViewTransactions.getItems().setAll(api.stats.getTransactionsSimple());
    tableViewTaps.getItems().setAll(api.stats.getTapsSimple());
    tableViewUsers.getItems().setAll(api.user.get());
    tableViewRoutes.getItems().setAll(api.map.getRoutesSimple());
  }
}
