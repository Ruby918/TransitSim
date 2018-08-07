package api;

import transit.*;

public class CardApi extends ChildApi {
  public CardApi(TransitFareManager transitFareManager, TransitLogger logger) {
    super(transitFareManager, logger);
  }

  public Card create(UserAccount user, String cardNickname) {
    Card card = transitFareManager.issueCard(user, cardNickname);
    logger.log.fine("Issuing card " + card + " to user " + user);
    return card;
  }

  public void load(Card card, double amount){
    logger.log.fine("Loading funds onto card " + card + " ($" + amount + ")");
    card.addAmount(amount);
  }

  public double getMoney(Card card) {
    logger.log.fine("Getting balance for card " + card);
    return card.getBalance();
  }

  public void addPriceModifier(Card card, PriceModifier priceModifier){
    logger.log.fine("Adding price modifier " + priceModifier + " to card " + card);
    card.setPriceModifier(priceModifier);
  }

}
