package transit;/*  Dan */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that models the functionality of a transit Customer account.
 *
 * @author group 0136
 */
public class UserAccount implements Serializable {

  // Instance variables storing information on user.
  private String name;
  private String email;
  private String password;
  private ArrayList<Card> cards = new ArrayList<>();
  private int id;
  private boolean active = true;
  public boolean isAdmin;

  /**
   * A constructor for the UserAccount class that sets a name, email and id.
   */
  public UserAccount(String name, String email, int id, boolean isAdmin) {
    this.name = name;
    this.email = email;
    this.id = id;
    this.password = "root";
    this.isAdmin = isAdmin;
  }

  public boolean hasCard(){ return cards.size() != 0;}

  /**
   * A getter which returns the id of the Customer account.
   *
   * @return - id of the Customer account.
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the Card of Customer account given the cards id.
   *
   * @param id - the id of the card which will be returned.
   * @return - Card with the id matching the given id or null if no such card is found.
   */
  public Card getCard(int id) {

    for (Card card : this.cards) {
      if (id == card.getCardId()) {
        return card;
      }
    }
    return null;
  }

  public void deactivateAccount() {
    this.active = false;
  }

  public void activateAccount() {
    this.active = true;
  }

  public boolean validatePassword(String password) {
    return this.password.equals(password);
  }

  /**
   * A getter which returns an array list of cards belonging to the Customers account.
   *
   * @return - Array list of cards belonging to the Customers account.
   */
  public ArrayList<Card> getCards() {
    return cards;
  }

  /**
   * A getter which returns the name of the Customer account.
   *
   * @return - name of the Customer account.
   */
  public String getName() {
    return name;
  }

  /**
   * A setter which changes the name of the Customer account.
   *
   * @param name - name of the Customer account.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * A getter which returns the email of the Customer account.
   *
   * @return - email of the Customer account.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Returns an array list of trips taken by the user.
   *
   * @return - array list of trips taken by the user.
   */
  public ArrayList<Trip> listAllTrips() {
    ArrayList<Trip> trips = new ArrayList<>();
    for (Card card : cards) {
      trips.addAll(card.getTrips());
    }
    return trips;
  }

  public ArrayList<Transaction> listAllTransactions() {
    ArrayList<Transaction> transactions = new ArrayList<>();
    for (Card card : cards) {
      transactions.addAll(card.getTransactions());
    }
    return transactions;
  }

  /**
   * Adds new card to the customers account array list.
   *
   * @param card - Card of which to add to customers accounts array list of cards.
   */
  public void addCard(Card card) {
    if (!this.cards.contains(card)) {
      this.cards.add(card);
    }
  }

  /**
   * Removes new card to the customers account array list.
   *
   * @param card - Card of which to remove to customers accounts array list of cards.
   */
  public void removeCard(Card card) {
    this.cards.remove(card);
  }

  /**
   * Returns an array list of the last 3 trips taken by user account.
   *
   * @return - array list of the last 3 trips taken by user account.
   */
  public ArrayList<Trip> calculateRecentTrips() {
    ArrayList<Trip> trips = listAllTrips();
    Collections.sort(trips);
    if (trips.size() <= 3) {
      return trips;
    } else {
      return new ArrayList<>(trips.subList(trips.size() - 3, trips.size()));
    }
  }

  /**
   * Returns the average monthly cost of user.
   *
   * @return - average monthly cost of user.
   */
  public double calculateAverageMonthlyCost() {
    // array lists needed to track customers data.
    ArrayList<TransitDate> months = new ArrayList<>();
    ArrayList<Double> costs = new ArrayList<>();
    ArrayList<Transaction> transactions = listAllTransactions();

    if (transactions.size() == 0) {
      return 0;
    }

    for (Transaction transaction : transactions) {
      boolean amountAdded = false;
      for (int j = 0; j < months.size(); j++) {
        if (transaction.getDate().inSameMonth(months.get(j))) {
          costs.set(j, costs.get(j) + transaction.getAmount());
          amountAdded = true;
          break;
        }
      }
      if (!amountAdded) {
        months.add(transaction.getDate());
        costs.add(transaction.getAmount());
      }
    }

    // return average
    double sum = 0;
    for (double cost : costs) {
      sum += cost;
    }
    if (sum == 0) {
      return 0;
    }
    return sum / costs.size();
  }

  /**
   * Returns string representation of customerAccountClass.
   *
   * @return - string representation of customerAccountClass.
   */
  @Override
  public String toString() {

    PrettyList<Card> prettyCards = new PrettyList<>(cards, "Cards");

    String st = "Name: "
        + this.name
        + " | Email: "
        + this.email
        + " | ID: "
        + Integer.toString(this.id)
        + " | Cards: "
        + prettyCards.toString();

    if (isAdmin) st += " (Admin)";
    return st;
  }
}
