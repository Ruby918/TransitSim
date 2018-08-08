package api;

import transit.*;
import util.EasyLogger;
import util.FormattedDate;

public class CardApi extends ChildApi {

  public CardApi(TransitFareManager transitFareManager, StatisticsManager statisticsManager,
      EasyLogger logger) {
    super(transitFareManager, statisticsManager, logger);
  }

  public Card create(UserAccount user, String cardNickname) {
    Card card = transitFareManager.issueCard(user, cardNickname);
    logger.log.fine("Issuing card " + card + " to user " + user);
    return card;
  }

  public void load(Card card, double amount) {
    logger.log.fine("Loading funds onto card " + card + " - adding " + amount);
    card.addAmount(amount);
  }

  public String getBalanceString(Card card) {
    logger.log.fine("Getting balance for card " + card);
    return card.getBalanceString();
  }

  public void addPriceModifier(Card card, PriceModifier priceModifier) {
    logger.log.fine("Adding price modifier " + priceModifier + " to card " + card);
    card.setPriceModifier(priceModifier);
  }

  public void update(Card card, String name, boolean isActive) {
    card.setNickname(name);
    card.setActive(isActive);
    logger.log.fine("Updated card " + card.getNickname());
  }


  public void tapIn(Station station, Card card, String dateString, String timeString)
      throws TapFailedException {
    FormattedDate date = new FormattedDate(dateString, timeString);
    logger.log.fine("Tapping into " + station + " on " + dateString + " with card " + card);
    try {
      card.tapIn(station, date);
    } catch (Exception e) {
      logger.log.warning("Tap in failed.");
      throw new TapFailedException();
    }
  }

  public void tapOut(Station station, Card card, String dateString, String timeString)
      throws TapFailedException {
    FormattedDate date = new FormattedDate(dateString, timeString);
    logger.log.fine("Tapping out of " + station + " on " + dateString + " with card " + card);
    try {
      card.tapOut(station, date);
    } catch (Exception e) {
      logger.log.warning("Tap out failed.");
      throw new TapFailedException();
    }
  }

}
