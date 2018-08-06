package api;

import transit.*;

public class CardApi extends ChildApi {
  public CardApi(TransitFareManager transitFareManager, TransitLogger logger) {
    super(transitFareManager, logger);
  }
}
