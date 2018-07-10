/* Danya */

import java.util.ArrayList;
import java.util.Date;

public class TransitFareManager {

  private ArrayList<CustomerAccount> customers = new ArrayList<>();
  private ArrayList<Card> cards = new ArrayList<>();
  private Map map;

  public TransitFareManager(Map map) {
    this.map = map;
  }

  public static void main(String[] args) {
    // Create map
    Map thisMap = new Map();
    MapDataParser mapData = new MapDataParser("map.txt", thisMap);
    mapData.parse();

    // Create transit fare manager
    TransitFareManager ttc = new TransitFareManager(thisMap);

    // Process events from events.txt
    EventDataParser eventData = new EventDataParser("events.txt", ttc);
    eventData.parse();
  }

  public String getDailyReport(Date date) {
    // stub
    return "";
  }

  public CustomerAccount createCustomerAccount(String name, String email) {
    // Increment customer id by one for every new customer
    CustomerAccount customer = new CustomerAccount(name, email, this.customers.size());
    this.customers.add(customer);
    return customer;
  }

  public CustomerAccount getCustomerById(int id) {
    return this.customers.get(id);
  }

  public Card getCardById(int id) {
    return this.cards.get(id);
  }

  public void transferCardToCustomer(Card card, CustomerAccount customer) {
    customer.addCard(card);
  }

  public Card generateCard(CustomerAccount customer) {
    // Increment card id by one for every new card
    Card card = new Card(this.cards.size());
    this.cards.add(card);
    return card;
  }

}
