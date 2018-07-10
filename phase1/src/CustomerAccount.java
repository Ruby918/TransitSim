/*  Dan */

import java.util.ArrayList;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void addCard(Card card) {
    this.cards.add(card);
  }

  public void removeCard(Card card) {
    this.cards.remove(card);
  }

  private String listCards() {
    String listCards = "";
    for (int i = 0; i < this.cards.size(); i++) {
      listCards += this.cards.get(i).toString() + ", ";
    }
    return listCards.substring(0, listCards.length() - 1);
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

  @Override
  public String toString() {
    return "Name: "
        + this.name
        + " | Email: "
        + this.email
        + " | ID: "
        + Integer.toString(this.id)
        + " | Cards: "
        + this.name
        + listCards();
  }
}