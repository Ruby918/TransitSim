/* loic */

package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TitleScreen extends Application {
  public static void main(String args[]) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("title_screen.fxml"));

    Scene scene = new Scene(root, 270, 150);
    stage.setScene(scene);
    stage.show();
  }
}
