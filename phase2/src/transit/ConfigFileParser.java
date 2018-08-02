package transit;/* Danya */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for parsing a configuration file.
 */
public abstract class ConfigFileParser {

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
  protected abstract void parseLine(String line);

  /**
   * Indents the given string by 13 spaces.
   *
   * @param str string to indent
   * @return indented string
   */
  public String indentString(String str) {
    return "             " + str
        .replace(System.lineSeparator(), System.lineSeparator() + "             ");
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