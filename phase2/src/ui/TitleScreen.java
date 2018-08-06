/* loic */

package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TitleScreen extends Application {

  public static void view() {
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("template/title_screen.fxml"));
    root.getStylesheets().add(getClass().getResource("template/styles.css").toExternalForm());
    Scene scene = new Scene(root, 270, 150);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop(){
    UiController.api.saveApplicationState();
  }
}
