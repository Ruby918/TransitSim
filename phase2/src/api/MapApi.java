package api;

import transit.*;

public class MapApi extends ChildApi{
  public MapApi(TransitFareManager transitFareManager, TransitLogger logger) {
    super(transitFareManager, logger);
  }
}
