package transit;/* Danya */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Main class of the transit system.
 */
public class TransitFareManager implements Serializable {

  /**
   * List of customers that have accounts with this transit system.
   */
  private ArrayList<UserAccount> customers = new ArrayList<>();
  /**
   * List of cards that have been issued.
   */
  private ArrayList<Card> cards = new ArrayList<>();
  /**
   * Map of this transit system.
   */
  protected Map map;

  private ArrayList<Trip> trips = new ArrayList<>();
  private ArrayList<Transaction> transactions = new ArrayList<>();
  public final double MAX_CHARGE = 6;

  public TransitFareManager(Map map) {
    this.map = map;
  }

  public ArrayList<UserAccount> getCustomers() {
    return customers;
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public ArrayList<Trip> getTrips() {
    return this.trips;
  }

  public ArrayList<Transaction> getTransactions() {
    return this.transactions;
  }

  public Map getMap() {
    return map;
  }

  /**
   * Creates and returns a new customer account. Generates ID for new customer based on number of
   * existing customers.
   *
   * @param name customer name
   * @param email customer email
   * @return new customer account object
   */
  public UserAccount createCustomerAccount(String name, String email, boolean isAdmin) {
    // Increment customer id by one for every new customer
    UserAccount customer = new UserAccount(name, email, this.customers.size(), isAdmin);
    this.customers.add(customer);
    return customer;
  }

  /**
   * Creates and returns a new card object. Generates ID for new card based in the number of
   * existing cards. Adds this new card to customer.
   *
   * @param customer customer to which the card is issued
   * @return new card object
   */
  public Card issueCard(UserAccount customer) {
    // Increment card id by one for every new card
    Card card = new Card(this.cards.size(), this);
    this.cards.add(card);
    customer.addCard(card);
    return card;
  }

  /**
   * Returns a customer based on the customer's ID. Because the IDs were generated incrementally, a
   * customer's ID is also the customer object's index in the list this.customers.
   *
   * @param id id of customer
   */
  public UserAccount getCustomerById(int id) {
    return this.customers.get(id);
  }

  public UserAccount getCustomerByEmail(String email) throws CustomerNotFoundException {
    for (UserAccount customer : this.customers) {
      if (customer.getEmail().equals(email)) {
        return customer;
      }
    }
    throw new CustomerNotFoundException();
  }

  /**
   * Returns a card based on the card's ID. Because the IDs were generated incrementally, a card's
   * ID is also the card object's index in the list this.cards.
   *
   * @param id id of card
   */
  public Card getCardById(int id) {
    return this.cards.get(id);
  }

  public Trip createTrip() {
    Trip trip = new Trip(MAX_CHARGE);
    this.trips.add(trip);
    return trip;
  }

  public Transaction createTransaction(Card card, Price price, TransitDate date) {
    Transaction transaction = new Transaction(card, price, date);
    this.transactions.add(transaction);
    return transaction;
  }

}
