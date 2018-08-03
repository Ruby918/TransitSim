package ui;

import api.Api;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Control;
import transit.UserAccount;
import transit.TransitLogger;
import transit.Card;

public class UiController {
  public static Api api;
  public static TransitLogger logger;
  public static UserAccount user;
  public static Card card;

  public void loadTemplate(Control node, String template) {
    loadTemplate(node, template, true);
  }

  public void loadTemplate(String template) {
    loadTemplate(null, template, false);
  }

  public void loadTemplate(Control node, String template, boolean hideWindow) {
    try {
      Stage stage = makeStage(template);
      stage.show();
      if (hideWindow) {
        Window owner = node.getScene().getWindow();
        owner.hide();
      }
    } catch (IOException e) {
      logger.log.severe("Failed to load " + template + " from nav.");
    }
  }

  private Stage makeStage(String template) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(template));
    Scene scene = new Scene(loader.load(), 800, 700);
    Stage loginStage = new Stage();
    loginStage.setScene(scene);
    return loginStage;
  }
}
