package api;

import transit.TransitFareManager;
import transit.TransitLogger;

public class ChildApi {

  protected TransitFareManager transitFareManager;
  protected TransitLogger logger;

  public ChildApi(TransitFareManager transitFareManager, TransitLogger logger) {
    this.transitFareManager = transitFareManager;
    this.logger = logger;
  }
}
