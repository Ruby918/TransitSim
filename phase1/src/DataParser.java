import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

abstract public class DataParser {

  private String fileName;

  public DataParser(String fileName) {
    this.fileName = fileName;
  }

  public static String indentString(String str) {
    return "             " + str.replace(System.lineSeparator(), System.lineSeparator() + "             ");
  }

  public static String getStringFromList(ArrayList list, String itemName) {
    String ret = "";
    if (list.size() == 0) return "No " + itemName + ".";
    for (Object item : list ) {
      ret += item.toString() + ", ";
    }
    ret = ret.substring(0, ret.length() - 2);
    return ret;
  }

  public static String getStringFromListMultiline(ArrayList list, String itemName) {
    String ret = "";
    if (list.size() == 0) return "No " + itemName + ".";
    for (Object item : list) {
      ret += item.toString() + System.lineSeparator();
    }
    return ret.trim();
  }

  public void parse() {
    try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
      String line = fileReader.readLine();
      while (line != null) {
        this.parseLine(line);
        line = fileReader.readLine();
      }
    } catch (IOException e) {
      System.out.println("Cannot read data file: " + fileName);
    }
  }

  abstract protected void parseLine(String line);
}
