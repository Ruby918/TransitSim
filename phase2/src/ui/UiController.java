package ui;

import api.Api;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import javafx.stage.Window;
import util.EasyLogger;

public class UiController {
  public static Api api;
  public static EasyLogger logger = new EasyLogger("ui");

  //UI runtime data store
  public static UiDataStore dataStore = new UiDataStore();

  protected void loadTemplate(String template, Control control) {
    Window owner = control.getScene().getWindow();

    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(template));
      Scene scene = new Scene(loader.load(), 600, 600);
      Stage stage = new Stage();
      stage.setScene(scene);
      owner.hide();
      stage.show();
    } catch (IOException e) {
      logger.log.severe("Failed to load " + template);
    }
  }
}
