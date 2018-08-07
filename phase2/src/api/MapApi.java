package api;

import transit.*;
import util.EasyLogger;

public class MapApi extends ChildApi{
  public MapApi(TransitFareManager transitFareManager, EasyLogger logger) {
    super(transitFareManager, logger);
  }
}
