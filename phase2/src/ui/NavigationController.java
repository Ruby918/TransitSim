package ui;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import transit.UserAccount;

public class NavigationController extends UiController implements Initializable {

  private UserAccount user;

  @FXML
  protected MenuBar menuBar;

  @FXML
  protected void handleLogoutButtonAction(ActionEvent event) {
    dataStore.set("currentUser", null);
    loadTemplate(TITLE_SCREEN);
  }

  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    loadTemplate(LOGIN_SCREEN);
  }

  @FXML
  protected void handleMyAccountButtonAction(ActionEvent event) {
    loadTemplate(HOMEPAGE_SCREEN);
  }

  @FXML
  protected void handleUsersButtonAction(ActionEvent event) {
    loadTemplate(USERS_SCREEN);
  }

  @FXML
  protected void handleStatisticsButtonAction(ActionEvent event) {
    loadTemplate(STATS_SCREEN);
  }

  @FXML
  protected void handleMapButtonAction(ActionEvent event) {
    loadTemplate(MAP_SCREEN);
  }

  @FXML
  public void initialize(java.net.URL arg0, ResourceBundle arg1) {

    user = (UserAccount) dataStore.get("currentUser").data();

    if (user != null) {
      Label menuLogoutLabel = addMenuLabel("Logout");
      menuLogoutLabel.setOnMouseClicked(event -> handleLogoutButtonAction(null));
      Label menuAccountLabel = addMenuLabel("My Account");
      menuAccountLabel.setOnMouseClicked(event -> handleMyAccountButtonAction(null));
      if (user.isAdmin()) {
        createAdminMenu();
      }
    }
    else {
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

  private void createAdminMenu() {
    Menu menuAdmin = new Menu("Admin");
    MenuItem menuItem1 = new MenuItem("Users");
    menuItem1.setOnAction(event -> handleUsersButtonAction(null));
    MenuItem menuItem2 = new MenuItem("Statistics");
    menuItem2.setOnAction(event -> handleStatisticsButtonAction(null));
    MenuItem menuItem3 = new MenuItem("Map");
    menuItem3.setOnAction(event -> handleMapButtonAction(null));
    menuAdmin.getItems().addAll(menuItem1, menuItem2, menuItem3);
    menuBar.getMenus().add(menuAdmin);
  }

  private void loadTemplate(String template) {
    super.loadTemplate(template, menuBar);
  }
}
