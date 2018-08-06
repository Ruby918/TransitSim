package api;

import transit.*;

public class UserApi extends ChildApi {
  public UserApi(TransitFareManager transitFareManager, TransitLogger logger) {
    super(transitFareManager, logger);
  }
  public UserAccount create(String name, String email, boolean isAdmin) {
    return transitFareManager.createUserAccount(name, email, isAdmin);
  }
}
