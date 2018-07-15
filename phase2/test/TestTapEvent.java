import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTapEvent {

  @Test
  void TestTapInEventStation() {

    Route sr = new SubwayRoute("SR");
    Station st = new SubwayStation("ST", sr);

    TapInEvent te = new TapInEvent(st, TransitDate.createFromDateString("10/10/2010"), null);
    assertEquals(te.getStation(), st);
  }

}
