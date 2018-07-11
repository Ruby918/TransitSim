/* Danya */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for parsing a configuration file.
 */
abstract public class ConfigFileParser {

  /**
   * Name of the file containing the configuration to be parsed.
   */
  private String fileName;

  public ConfigFileParser(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Parses a line this object's configuration file.
   *
   * @param line line in configuration file to be parsed
   */
  abstract protected void parseLine(String line);

  /**
   * Indents the given string by 13 spaces.
   *
   * @param str string to indent
   * @return indented string
   */
  public static String indentString(String str) {
    return "             " + str
        .replace(System.lineSeparator(), System.lineSeparator() + "             ");
  }

  /**
   * Turns an arraylist of objects into a comma-separated string by calling toString on each of
   * them. If there are no objects, returns the string "No \<categoryName\>." using parameter
   * categoryName.
   *
   * @param list arraylist of objects
   * @param categoryName name of this category of objects
   * @return comma-separated list of object strings
   */
  public static String getStringFromList(ArrayList list, String categoryName) {
    String ret = "";
    if (list.size() == 0) {
      return "No " + categoryName + ".";
    }
    for (Object item : list) {
      ret += item.toString() + ", ";
    }
    ret = ret.substring(0, ret.length() - 2);
    return ret;
  }

  /**
   * Turns an arraylist of objects into a newline-separated string by calling toString on each of
   * them. If there are no objects, returns the string "No \<categoryName\>." using parameter
   * categoryName.
   *
   * @param list arraylist of objects
   * @param categoryName name of this category of objects
   * @return newline-separated list of object strings
   */
  public static String getStringFromListMultiline(ArrayList list, String categoryName) {
    String ret = "";
    if (list.size() == 0) {
      return "No " + categoryName + ".";
    }
    for (Object item : list) {
      ret += item.toString() + System.lineSeparator();
    }
    return ret.trim();
  }

  /**
   * Parses this object's configuration file by calling the abstract method parseLine on each line
   * of file.
   */
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
}
