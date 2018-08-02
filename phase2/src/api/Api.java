package api;

import java.util.ArrayList;
import transit.CustomerAccount;
import transit.StatisticsManager;
import transit.TransitFareManager;
import transit.TransitLogger;

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
}
