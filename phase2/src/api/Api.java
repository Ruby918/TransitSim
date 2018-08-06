package api;

import java.util.ArrayList;

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
  public void loadMoney(Card card, double amount){card.addAmount(amount);}

  public UserAccount loginCustomer(String email, String password) throws LoginFailedException {
    try {
      UserAccount user = transitFareManager.getCustomerByEmail(email);
      if (user.validatePassword(password)) {
        logger.log.fine("Successfully logged in user with email " + email);
        return user;
      }
      throw new LoginFailedException();
    } catch (CustomerNotFoundException e) {}
    logger.log.warning("Failed failed to log in user with email " + email);
    throw new LoginFailedException();
  }

  // UI needs to know how much money in a card so it can display it.
  public double getMoney(Card card) {
    logger.log.fine("Getting balance for card " + card);
    return card.getBalance();
  }

  //stats info

    public double getRevenueOnDate(String dateString){
        TransitDate date = new TransitDate(dateString);
        logger.log.fine("Getting revenue for date " + dateString);
        return this.statisticsManager.calculateRevenueOnDate(date);
    }

    public double getTotalRevenueOnDate(){
        return this.statisticsManager.calculateRevenue();
    }

    public ArrayList<Station> getStationsReachedOnDate(String dateString){
      TransitDate date = new TransitDate(dateString);
      logger.log.fine("Getting stations reached on date " + dateString);
      return this.statisticsManager.getStationsReachedOnDate(date);
    }


  public void tapIn(Station station, Card card, String dateString) {
    TransitDate date = new TransitDate(dateString);
    logger.log.fine("Tapping into " + station + " on " + dateString + " with card " + card);
    try{
    card.tapIn(station, date);
    }
    catch (Exception e){
      logger.log.warning("Tap in failed.");

    }
  }

  public void tapOut(Station station, Card card, String dateString) {
    TransitDate date = new TransitDate(dateString);
    logger.log.fine("Tapping out of " + station + " on " + dateString + " with card " + card);
    try{
      card.tapOut(station, date);
    }
    catch (Exception e){
      logger.log.warning("Tap out failed.");
    }
  }
  public void addPriceModifier(Card card, PriceModifier priceModifier){
    logger.log.fine("Adding price modifier " + priceModifier + " to card " + card);
    card.setPriceModifier(priceModifier);
  }
}
