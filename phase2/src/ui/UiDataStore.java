package ui;

import java.util.HashMap;
import java.util.Map;

public class UiDataStore {

  private Map<String, UiData> data = new HashMap<>();

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
