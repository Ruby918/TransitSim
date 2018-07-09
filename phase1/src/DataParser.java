import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

abstract public class DataParser {

  public DataParser(String fileName) {
    try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
      String line = fileReader.readLine();
      while (line != null) {
        this.parseLine(line);
        line = fileReader.readLine();
      }
    } catch (IOException e) {
      System.out.println("Cannot read data file.");
    }
  }

  abstract protected void parseLine(String line);
}
