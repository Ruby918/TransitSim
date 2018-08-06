package api;

import transit.*;

public class UserApi extends ChildApi {
  public UserApi(TransitFareManager transitFareManager, TransitLogger logger) {
    super(transitFareManager, logger);
  }
  public UserAccount create(String name, String email, boolean isAdmin) {
    return transitFareManager.createUserAccount(name, email, isAdmin);
  }

  public void update(String oldEmail, String name, String newEmail, boolean isAdmin) {
    transitFareManager.updateUser(oldEmail, name, newEmail, isAdmin);
  }

  public void delete(String email) {
    transitFareManager.deleteUser(email);
  }
}
