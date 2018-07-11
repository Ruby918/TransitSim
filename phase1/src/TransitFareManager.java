/* Danya */

import java.util.ArrayList;

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
    MapConfigFileParser mapData = new MapConfigFileParser("map.txt", thisMap);
    mapData.parse();

    // Create transit fare manager
    TransitFareManager ttc = new TransitFareManager(thisMap);

    // Process events from events.txt
    EventConfigFileParser eventData = new EventConfigFileParser("events.txt", ttc);
    eventData.parse();
  }

  public Map getMap() {
    return this.map;
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

  public ArrayList<Route> getRoutes() {
    return map.getRoutes();
  }

  public ArrayList<CustomerAccount> getCustomers() {
    return customers;
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public Card generateCard(CustomerAccount customer) {
    // Increment card id by one for every new card
    Card card = new Card(this.cards.size());
    this.cards.add(card);
    customer.addCard(card);
    return card;
  }

}
