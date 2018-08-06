package api;

import java.util.ArrayList;
import java.util.Date;

import transit.*;

public class Api {

  private TransitFareManager transitFareManager;
  private StatisticsManager statisticsManager;
  private TransitLogger logger;

  public Api(TransitFareManager transitFareManager, StatisticsManager statisticsManager, TransitLogger transitLogger) {
    this.transitFareManager = transitFareManager;
    this.statisticsManager= statisticsManager;
    this.logger = transitLogger;
  }

  public ArrayList<UserAccount> getUsers() {
    return transitFareManager.getCustomers();
  }

  public void createCard(UserAccount customer) {
    transitFareManager.issueCard(customer);
  }
  public void addMoney(Card card, double amount){card.addAmount(amount);}

  public UserAccount loginCustomer(String email, String password) throws LoginFailedException {
    try {
      UserAccount user = transitFareManager.getCustomerByEmail(email);
      if (user.validatePassword(password)) {
        logger.log.fine("Api has successfully logged in user with email " + email);
        return user;
      }
      throw new LoginFailedException();
    } catch (CustomerNotFoundException e) {}
      throw new LoginFailedException();
  }

  // UI needs to know how much money in a card so it can display it.
  public double getMoney(Card card) {
    return card.getBalance();
  }

  //stats info
    public ArrayList<Trip> getDailyProfits(TransitDate date){
      return this.statisticsManager.getTripsOnDate(date);
    }

    public double getRevenueOnDate(TransitDate date){
        return this.statisticsManager.calculateRevenueOnDate(date);
    }

    public double getTotalRevenueOnDate(){
        return this.statisticsManager.calculateRevenue();
    }

    public ArrayList<Station> getStationsReachedOnDate(TransitDate date){
        return this.statisticsManager.getStationsReachedOnDate(date);
    }


  public void handleTapIn() {

  }

  public void handleTapOut() {

  }
}
