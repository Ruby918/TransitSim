package ui;

import java.util.HashMap;
import java.util.Map;

public class UiDataStore {

  private Map<String, UiData> data = new HashMap<>();

  static final String CURRENT_USER = "currentUser";
  static final String CURRENT_FILTER_DATE = "currentFilterDate";
  static final String CURRENT_STATION = "currentStation";
  static final String CURRENT_CARD = "currentCard";

  public void set(String name, UiData uiData) {
    if (uiData == null) data.put(name, new UiData<>(null));
    else data.put(name, uiData);
  }

  public UiData get(String name) {
    UiData uiData = data.get(name);
    if (uiData == null) return new UiData<>(null);
    return uiData;
  }

}
