package ui;

import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import transit.Route;
import transit.Station;
import util.PrettyList;

public class EditStationController extends UiController {

  private Station station;
  private ArrayList<Station> currentAdjacentStations = new ArrayList<>();

  @FXML
  private Label editStationLabel;

  @FXML
  private TextField nameField;

  @FXML
  private ComboBox<Route> selectRouteCombo;

  @FXML
  private ComboBox<Station> selectStationCombo;

  @FXML
  private Label adjacentStationsListLabel;

  @FXML
  public void initialize() {
    station = (Station) dataStore.get("currentStation").data();
    populateRoutes();
    populateStations();
    if (station != null) {
      editStationLabel.setText(station.toString());
      nameField.setText("Edit " + station.getName());
      selectRouteCombo.getSelectionModel().select(station.getRoute());
      // update current adjacent stations
      currentAdjacentStations.clear();
      currentAdjacentStations.addAll(station.getAdjacent());
    } else {
      editStationLabel.setText("No station is currently selected.");
      nameField.setText("");
      selectRouteCombo.getSelectionModel().clearSelection();
      currentAdjacentStations.clear();
    }
    populateAdjacentStations();
  }

  @FXML
  public void handleAddButton() {
    currentAdjacentStations.add(selectStationCombo.getSelectionModel().getSelectedItem());
    populateAdjacentStations();
  }

  @FXML
  public void handleRemoveButton() {
    currentAdjacentStations.remove(selectStationCombo.getSelectionModel().getSelectedItem());
    populateAdjacentStations();
  }

  private void populateRoutes() {
    ArrayList<Route> routes;
    selectRouteCombo.getItems().clear();
    if (station == null) routes = api.map.getRoutes();
    else routes = api.map.getRoutes(station);
    selectRouteCombo.getItems().addAll(routes);
  }

  private void populateStations() {
    selectStationCombo.getItems().addAll(api.map.getStations());
  }

  private void populateAdjacentStations() {
    PrettyList<Station> stationsList = new PrettyList<>(currentAdjacentStations, "Stations");
    adjacentStationsListLabel.setText(stationsList.toString());
  }
}
