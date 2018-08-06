package ui;

import api.Api;
import transit.TransitLogger;
import transit.Card;
import transit.Station;
import transit.PriceModifier;

public class UiController {
  public static Api api;
  public static TransitLogger logger;

  //the current user, card, station, etc. being looked at by the screen
  public static Card card;
  public static Station station;
  public static PriceModifier priceModifier;
  public static UiDataStore dataStore = new UiDataStore();
}
