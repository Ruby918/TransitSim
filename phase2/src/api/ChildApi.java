package api;

import transit.TransitFareManager;
import util.EasyLogger;

public class ChildApi {

  protected TransitFareManager transitFareManager;
  protected transient EasyLogger logger;

  public ChildApi(TransitFareManager transitFareManager, EasyLogger logger) {
    this.transitFareManager = transitFareManager;
    this.logger = logger;
  }
}
