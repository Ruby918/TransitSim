package api;

import java.util.ArrayList;
import transit.*;
import transit.simplemodel.SimpleUser;
import util.EasyLogger;

public class UserApi extends ChildApi {

  public UserApi(TransitFareManager transitFareManager, StatisticsManager statisticsManager,EasyLogger logger) {
    super(transitFareManager, statisticsManager,logger);
  }

  public UserAccount create(String name, String email, boolean isAdmin) throws CreateUserException {
    if (name.isEmpty() || email.isEmpty() || (transitFareManager.getUserByEmail(email) != null)) {
      logger.log.warning("Failed to create new user");
      throw new CreateUserException();
    }
    return transitFareManager.createUserAccount(name, email, isAdmin);
  }

  public void updateName(String email, String name) throws UpdateUserException {
    UserAccount user = transitFareManager.getUserByEmail(email);
    if (user != null) user.setName(name);
    else throw new UpdateUserException();
  }

  public void updatePassword(String email, String password) throws UpdateUserException {
    UserAccount user = transitFareManager.getUserByEmail(email);
    if (user != null) user.setPassword(password);
    else throw new UpdateUserException();
  }

  public void update(String oldEmail, String name, String newEmail, String password, boolean isAdmin)
      throws UpdateUserException {
    if (oldEmail.isEmpty() || name.isEmpty() || newEmail.isEmpty() || password.isEmpty() || (
        (transitFareManager.getUserByEmail(newEmail) != null) && (!newEmail.equals(oldEmail)))) {
      logger.log.warning("Failed to update user with email " + oldEmail);
      throw new UpdateUserException();
    }
    transitFareManager.updateUser(oldEmail, name, newEmail, password, isAdmin);
  }

  public void delete(String email) {
    transitFareManager.deleteUser(email);
  }

  public ArrayList<SimpleUser> getSimple() {
    ArrayList<UserAccount> users = transitFareManager.getUsers();
    ArrayList<SimpleUser> result = new ArrayList<>();
    for (UserAccount user : users) {
      result.add(new SimpleUser(user));
    }
    return result;
  }

  public UserAccount login(String email, String password) throws LoginFailedException {
    try {
      UserAccount user = transitFareManager.getUserByEmail(email);
      if (user != null && user.validatePassword(password)) {
        logger.log.fine("Successfully logged in user with email " + email);
        return user;
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      logger.log.warning("Failed failed to log in user with email " + email);
      throw new LoginFailedException();
    }
  }

}
