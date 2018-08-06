package ui;

import api.Api;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import javafx.stage.Window;
import transit.TransitLogger;
import transit.Station;
import transit.PriceModifier;

public class UiController {
  public static Api api;
  public static TransitLogger logger;

  //UI runtime data store
  public static UiDataStore dataStore = new UiDataStore();

  protected void loadTemplate(String template, Control control) {
    Window owner = control.getScene().getWindow();

    try {
      FXMLLoader titleLoader = new FXMLLoader();
      titleLoader.setLocation(getClass().getResource(template));
      Scene loginScene = new Scene(titleLoader.load(), 600, 600);
      Stage loginStage = new Stage();
      loginStage.setScene(loginScene);
      owner.hide();
      loginStage.show();
    } catch (IOException e) {
      logger.log.severe("Failed to load " + template);
    }
  }
}
