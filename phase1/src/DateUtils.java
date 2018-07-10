import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
  public static String formatDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return sdf.format(date);
  }

  public static String formatDatetime(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    return sdf.format(date);
  }

  public static Date getDateFromDateString(String dateString) {
    String[] dateParts = dateString.split("/");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]) - 1;
    int year = Integer.parseInt(dateParts[2]);
    Calendar calendar = new GregorianCalendar(year, month, day);
    return calendar.getTime();
  }

  public static Date getDateFromDatetimeString(String datetimeString) {
    String[] datetimeParts = datetimeString.split(" ");
    String[] dateParts = datetimeParts[0].split("/");
    String[] timeParts = datetimeParts[1].split(":");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]) - 1;
    int year = Integer.parseInt(dateParts[2]);
    int hour = Integer.parseInt(timeParts[0]);
    int minute = Integer.parseInt(timeParts[1]);
    Calendar calendar = new GregorianCalendar(year, month, day, hour, minute, 0);
    return calendar.getTime();
  }

  public static boolean datesOnSameDay(Date date1, Date date2) {
    // stub
    return false;
  }
}