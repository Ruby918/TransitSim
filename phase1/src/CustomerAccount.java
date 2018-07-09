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

  public void setId(int id) {
    this.id = id;
  }

  public Card getCard(int id){

    for(int i = 0; i < this.cards.size(); i++){
      if (id == this.cards.get(i).getCardId()){
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

  public void loadMoney(Double money, Card card){
    while(money != 0){
      if (money >= 50){
        card.addFiftyDollars();
        money -= 50;
      } else if(money >= 20){
        card.addTwentyDollars();
        money -= 20;
      } else {
        card.addTenDollars();
        money -= 10;
      }
    }
  }


}