import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

abstract public class DataParser {

  private String fileName;

  public DataParser(String fileName) {
    this.fileName = fileName;
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
