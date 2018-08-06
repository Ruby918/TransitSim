package api;

import java.util.ArrayList;
import transit.*;

public class Api {

  public UserApi user;
  public CardApi card;
  public MapApi map;

  private TransitFareManager transitFareManager;
  private StatisticsManager statisticsManager;
  private TransitLogger logger;


  public Api(TransitLogger logger) {
    this.logger = logger;
  }

  private void init() {
    user = new UserApi(transitFareManager, logger);
    card = new CardApi(transitFareManager, logger);
    map = new MapApi(transitFareManager, logger);
  }

  public void loadApplicationStateFromFile(String fileName) throws DataReadException, DataWriteException {
    DataReadWrite<TransitFareManager> dataReadWrite = new DataReadWrite<>(fileName);
    transitFareManager = dataReadWrite.read();
    logger.log.fine("Successfully loaded application state from " + fileName);
    statisticsManager = new StatisticsManager(transitFareManager);
    init();
  }

  public void loadApplicationStateFromEventsTxt() {
    logger.log.fine("Creating new application state from events.txt.");
    // Create data from events.txt and map.txt
    Map map = new Map();
    MapConfigFileParser mapConfigFileParser = new MapConfigFileParser("map.txt", map, logger);
    mapConfigFileParser.parse();
    transitFareManager = new TransitFareManager(map);
    statisticsManager = new StatisticsManager(transitFareManager);
    EventConfigFileParser eventConfigFileParser = new EventConfigFileParser("events.txt", transitFareManager, statisticsManager, logger);
    eventConfigFileParser.parse();
    init();
  }

  public void saveApplicationState() {
   saveApplicationStateToFile("data/transitFareManager.ser");
  }

  public void saveApplicationStateToFile(String fileName) {
    try {
      DataReadWrite<TransitFareManager> dataReadWrite = new DataReadWrite<>(fileName);
      dataReadWrite.save(transitFareManager);
      logger.log.fine("Successfully saved application state to file " + fileName);
    } catch (Exception e) {
      logger.log.severe("Couldn't save application state to file " + fileName);
    }
  }

  public ArrayList<StationForTableView> getStations() {
    ArrayList<Station> stations =  transitFareManager.getMap().getStations();
    ArrayList<StationForTableView> result = new ArrayList<>();
    for (Station station : stations){
      result.add(new StationForTableView(station));
    }
    return result;
  }

  public void createCard(UserAccount customer) {
    transitFareManager.issueCard(customer);
  }
  public void loadMoney(Card card, double amount){card.addAmount(amount);}

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


  public void tapIn(Station station, Card card, String dateString, String timeStrring) {
    TransitDate date = new TransitDate(dateString, timeStrring);
    logger.log.fine("Tapping into " + station + " on " + dateString + " with card " + card);
    try{
    card.tapIn(station, date);
    }
    catch (Exception e){
      logger.log.warning("Tap in failed.");

    }
  }

  public void tapOut(Station station, Card card, String dateString, String timeStrring) {
    TransitDate date = new TransitDate(dateString, timeStrring);
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
