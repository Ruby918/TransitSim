package api;

import java.util.ArrayList;
import transit.CustomerAccount;
import transit.CustomerNotFoundException;
import transit.StatisticsManager;
import transit.TransitFareManager;
import transit.TransitLogger;
import transit.Card;

public class Api {

  private TransitFareManager transitFareManager;
  private StatisticsManager statisticsManager;
  private TransitLogger logger;

  public Api(TransitFareManager transitFareManager, StatisticsManager statisticsManager, TransitLogger transitLogger) {
    this.transitFareManager = transitFareManager;
    this.statisticsManager= statisticsManager;
    this.logger = transitLogger;
  }

  public ArrayList<CustomerAccount> getCustomers() {
    return transitFareManager.getCustomers();
  }

  public Card createCard(CustomerAccount customer) {
    return transitFareManager.issueCard(customer);
  }

  public CustomerAccount loginCustomer(String email, String password) throws LoginFailedException {
    try {
      CustomerAccount user = transitFareManager.getCustomerByEmail(email);
      if (user.validatePassword(password)) {
        return user;
      }
      throw new LoginFailedException();
    } catch (CustomerNotFoundException e) {}
      throw new LoginFailedException();
  }
}
