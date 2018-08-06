package transit;/* Danya */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main class of the transit system.
 */
public class TransitFareManager implements Serializable {

  /**
   * List of users that have accounts with this transit system.
   */
  private HashMap<String, UserAccount> users = new HashMap<>();
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

  public ArrayList<UserAccount> getUsers() {
    ArrayList<UserAccount> usersList = new ArrayList<>();
    usersList.addAll(users.values());
    return usersList;
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
   * Creates and returns a new user account. Generates ID for new user based on number of
   * existing users.
   *
   * @param name user name
   * @param email user email
   * @return new user account object
   */
  public UserAccount createUserAccount(String name, String email, boolean isAdmin) {
    // Increment user id by one for every new user
    UserAccount user = new UserAccount(name, email, isAdmin);
    this.users.put(email, user);
    return user;
  }

  /**
   * Creates and returns a new card object. Generates ID for new card based in the number of
   * existing cards. Adds this new card to user.
   *
   * @param customer user to which the card is issued
   * @return new card object
   */
  public Card issueCard(UserAccount customer) {
    // Increment card id by one for every new card
    Card card = new Card(this.cards.size(), this);
    this.cards.add(card);
    customer.addCard(card);
    return card;
  }

  public UserAccount getUserByEmail(String email) {
    UserAccount user = this.users.get(email);
    return user;
  }

  public void updateUser(String oldEmail, String name, String newEmail, boolean isAdmin) {
    UserAccount user = this.users.get(oldEmail);
    if (user != null) {
      user.setName(name);
      user.setEmail(newEmail);
      user.setAdmin(isAdmin);
    }
    users.remove(oldEmail);
    users.put(newEmail, user);
  }

  public void deleteUser(String email) {
    this.users.remove(email);
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
