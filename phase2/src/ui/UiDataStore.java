package ui;

import java.util.HashMap;
import java.util.Map;

public class UiDataStore {

  private Map<String, UiData> data = new HashMap<>();

  public void set(String name, UiData uiData) {
    data.put(name, uiData);
  }

  public UiData get(String name) {
    return data.get(name);
  }

}
