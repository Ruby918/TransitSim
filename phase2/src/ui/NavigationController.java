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
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NavigationController extends UiController implements Initializable {

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
  protected void handleCustomersButtonAction(ActionEvent event) {
    loadTemplate("template/admin_customers_screen.fxml");
  }

  @FXML
  protected void handleStatisticsButtonAction(ActionEvent event) {
    loadTemplate("template/admin_statistics_screen.fxml");
  }

  @FXML
  protected void handleMapButtonAction(ActionEvent event) {
    loadTemplate("template/admin_map_screen.fxml");
  }

  @FXML
  public void initialize(java.net.URL arg0, ResourceBundle arg1) {

    if (user != null) {
      loggedInLabel.setText("Logged in as " + user.getName());
      Label menuLogoutLabel = addMenuLabel("Logout");
      menuLogoutLabel.setOnMouseClicked(event -> handleLogoutButtonAction(null));
      if (user.isAdmin) {
        Menu menuAdmin = new Menu("Admin");
        MenuItem menuItem1 = new MenuItem("Customers");
        menuItem1.setOnAction(event -> handleCustomersButtonAction(null));
        MenuItem menuItem2 = new MenuItem("Statistics");
        menuItem2.setOnAction(event -> handleStatisticsButtonAction(null));
        MenuItem menuItem3 = new MenuItem("Map");
        menuItem3.setOnAction(event -> handleMapButtonAction(null));
        menuAdmin.getItems().addAll(menuItem1, menuItem2, menuItem3);
        menuBar.getMenus().add(menuAdmin);
      }
    }
    else {
      loggedInLabel.setText("");
      Label menuLoginLabel = addMenuLabel("Login");
      menuLoginLabel.setOnMouseClicked(event -> handleLoginButtonAction(null));
    }
  }

  private Label addMenuLabel(String label) {
    Menu menu = new Menu();
    Label menuLabel = new Label(label);
    menu.setGraphic(menuLabel);
    menuBar.getMenus().add(menu);
    return menuLabel;
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
      logger.log.severe("Failed to load " + template + " from nav.");
    }
  }
}
