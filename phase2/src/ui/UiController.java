package ui;

import api.Api;
import transit.TransitLogger;
import transit.Station;
import transit.PriceModifier;

public class UiController {
  public static Api api;
  public static TransitLogger logger;

  //UI runtime data store
  public static UiDataStore dataStore = new UiDataStore();
}
