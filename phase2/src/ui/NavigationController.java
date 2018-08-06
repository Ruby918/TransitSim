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
  protected Label loggedInLabel;


  @FXML
  protected void handleLogoutButtonAction(ActionEvent event) {
    dataStore.set("currentUser", null);
    loadTemplate("template/title_screen.fxml");
  }

  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    loadTemplate("template/user_login_screen.fxml");
  }

  @FXML
  protected void handleMyAccountButtonAction(ActionEvent event) {
    loadTemplate("template/user_screen.fxml");
  }

  @FXML
  protected void handleCustomersButtonAction(ActionEvent event) {
    loadTemplate("template/admin_users_screen.fxml");
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

    // get current user
    user = (UserAccount) dataStore.get("currentUser").data();

    if (user != null) {
      loggedInLabel.setText("Logged in as " + user.getName());
      Label menuLogoutLabel = addMenuLabel("Logout");
      menuLogoutLabel.setOnMouseClicked(event -> handleLogoutButtonAction(null));
      Label menuAccountLabel = addMenuLabel("My Account");
      menuAccountLabel.setOnMouseClicked(event -> handleMyAccountButtonAction(null));
      if (user.isAdmin) {
        createAdminMenu();
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

  private void createAdminMenu() {
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

  private void loadTemplate(String template) {
    super.loadTemplate(template, menuBar);
  }
}
