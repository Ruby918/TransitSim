package api;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import transit.UserAccount;

public class UserForTableView {

  private final SimpleStringProperty name;
  private final SimpleStringProperty email;
  private final SimpleBooleanProperty isAdmin;

  public UserForTableView(UserAccount user) {
    name = new SimpleStringProperty(user.getName());
    email = new SimpleStringProperty(user.getEmail());
    isAdmin = new SimpleBooleanProperty(user.isAdmin());
  }

  public String getName() {
    return name.get();
  }

  public void setName(String nameString) {
    name.set(nameString);
  }

  public String getEmail() {
    return email.get();
  }

  public void setEmail(String emailString) {
    email.set(emailString);
  }

  public boolean getIsAdmin() {
    return isAdmin.get();
  }

  public void setIsAdmin(boolean newIsAdmin) {
    isAdmin.set(newIsAdmin);
  }

}
