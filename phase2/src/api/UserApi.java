package api;

import java.util.ArrayList;
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

  public ArrayList<UserForTableView> get() {
    ArrayList<UserAccount> users =  transitFareManager.getUsers();
    ArrayList<UserForTableView> result = new ArrayList<>();
    for (UserAccount user : users){
      result.add(new UserForTableView(user));
    }
    return result;
  }

  public UserAccount login(String email, String password) throws LoginFailedException {
    try {
      UserAccount user = transitFareManager.getUserByEmail(email);
      if (user.validatePassword(password)) {
        logger.log.fine("Successfully logged in user with email " + email);
        return user;
      } else throw new Exception();
    } catch (Exception e) {
      logger.log.warning("Failed failed to log in user with email " + email);
      throw new LoginFailedException();
    }
  }

}
