/*  Dan */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class CustomerAccount {

  private String name;
  private String email;
  private ArrayList<Card> cards = new ArrayList<>();
  private int id;

  public CustomerAccount(String name, String email, int id) {
    this.name = name;
    this.email = email;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public Card getCard(int id) {

    for (int i = 0; i < this.cards.size(); i++) {
      if (id == this.cards.get(i).getCardId()) {
        return this.cards.get(i);
      }
    }
    return null;
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public ArrayList<Trip> getTrips() {
    ArrayList<Trip> trips = new ArrayList<>();
    for (Card card : cards) {
      trips.addAll(card.getTrips());
    }
    return trips;
  }

  public ArrayList<Date> getInvalidTapDates() {
    ArrayList<Date> invalidTapDates = new ArrayList<>();
    for (Card card : cards) {
      invalidTapDates.addAll(card.getInvalidTapEventDates());
    }
    return invalidTapDates;
  }

  public void addCard(Card card) {
    this.cards.add(card);
  }

  public void removeCard(Card card) {
    this.cards.remove(card);
  }

  public void loadMoney(Double money, int id) {
    Card card = getCard(id);
    while (money != 0) {
      if (money >= 50) {
        card.addFiftyDollars();
        money -= 50;
      } else if (money >= 20) {
        card.addTwentyDollars();
        money -= 20;
      } else {
        card.addTenDollars();
        money -= 10;
      }
    }
  }

  public ArrayList<Trip> getRecentTrips() {
    ArrayList<Trip> trips = getTrips();
    // The following line of code is from https://stackoverflow.com/a/44525425/3200577
    // (User: Stimpson Cat)
    trips.sort(Comparator.comparing(o -> o.getStartDate()));
    if (trips.size() <= 3) return trips;
    else return new ArrayList<>(trips.subList(trips.size() - 3, trips.size()));
  }

  public double getAverageMonthlyCost() {
    ArrayList<Date> months = new ArrayList<>();
    ArrayList<Double> costs = new ArrayList<>();
    ArrayList<Trip> trips = getTrips();
    ArrayList<Date> invalidTapDates = getInvalidTapDates();

    if ((trips.size() == 0) && invalidTapDates.size() == 0) return 0;

    // account for trip costs
    for (int i = 0; i < trips.size(); i++) {
      Trip trip = trips.get(i);
      boolean tripCostAdded = false;
      for (int j = 0; j < months.size(); j++) {
        if (DateUtils.datesInSameMonth(trip.getStartDate(), months.get(j))) {
          costs.set(j, costs.get(j) + trip.getCost());
          tripCostAdded = true;
          break;
        }
      }
      if (!tripCostAdded) {
        months.add(trip.getStartDate());
        costs.add(trip.getCost());
      }
    }
    // account for pathological tap costs
    for (int i = 0; i < invalidTapDates.size(); i++) {
      Date date = invalidTapDates.get(i);
      boolean tapCostAdded = false;
      for (int j = 0; j < months.size(); j++) {
        if (DateUtils.datesInSameMonth(date, months.get(j))) {
          costs.set(j, costs.get(j) + 6);
          tapCostAdded = true;
          break;
        }
      }
      if (!tapCostAdded) {
        months.add(date);
        costs.add(6.0);
      }
    }

    // return average
    double sum = 0;
    for (double cost : costs) {
      sum += cost;
    }
    if (sum == 0) return 0;
    return sum/costs.size();
  }

  @Override
  public String toString() {
    return "Name: "
        + this.name
        + " | Email: "
        + this.email
        + " | ID: "
        + Integer.toString(this.id)
        + " | Cards: "
        + DataParser.getStringFromList(cards, "Cards");
  }
}