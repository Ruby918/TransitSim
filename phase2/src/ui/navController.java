package ui;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NavController extends UiController implements Initializable {

  @FXML
  protected MenuBar menuBar;

  @FXML
  protected Label loggedInLabel;


  @FXML
  protected void handleLogoutButtonAction(ActionEvent event) {
    user = null;
    loadTemplate("template/title_screen.fxml");
  }

  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    loadTemplate("template/user_login_screen.fxml");
  }

  @FXML
  protected void handleAdminButtonAction(ActionEvent event) {
    loadTemplate("template/admin_screen.fxml");
  }

  @FXML
  public void initialize(java.net.URL arg0, ResourceBundle arg1) {

    if (user != null) {
      loggedInLabel.setText("Logged in as " + user.getName());
      Label menuLogoutLabel = addMenuLabel("Logout");
      menuLogoutLabel.setOnMouseClicked(event -> handleLogoutButtonAction(null));
      if (user.isAdmin) {
        Label menuAdminLabel = addMenuLabel("Admin");
        menuAdminLabel.setOnMouseClicked(event -> handleAdminButtonAction(null));
      }
    }
    else {
      loggedInLabel.setText("");
      Label menuLoginLabel = addMenuLabel("Login");
      menuLoginLabel.setOnMouseClicked(event -> handleLoginButtonAction(null));
    }
  }

  private Label addMenuLabel(String label) {
    Menu menuLogin = new Menu();
    Label menuLoginLabel = new Label(label);
    menuLogin.setGraphic(menuLoginLabel);
    menuBar.getMenus().add(menuLogin);
    return menuLoginLabel;
  }

  private void loadTemplate(String template) {
    Window owner = menuBar.getScene().getWindow();

    try {
      FXMLLoader titleLoader = new FXMLLoader();
      titleLoader.setLocation(getClass().getResource(template));
      Scene loginScene = new Scene(titleLoader.load(), 500, 500);
      Stage loginStage = new Stage();
      loginStage.setScene(loginScene);
      owner.hide();
      loginStage.show();
    } catch (IOException e) {
      logger.error("Failed to load " + template + " from nav.");
    }
  }
}
