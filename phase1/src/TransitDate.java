/* Danya */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TransitDate implements Comparable<TransitDate> {

  private Date date;

  public TransitDate(Date date) {
    this.date = date;
  }

  public Date getDate() {
    return date;
  }

  public String toDateString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return sdf.format(date);
  }

  public String toDateTimeString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    return sdf.format(date);
  }

  public static TransitDate createFromDateString(String dateString) {
    String[] dateParts = dateString.split("/");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]) - 1;
    int year = Integer.parseInt(dateParts[2]);
    Calendar calendar = new GregorianCalendar(year, month, day);
    return new TransitDate(calendar.getTime());
  }

  public static TransitDate createFromDatetimeString(String datetimeString) {
    String[] datetimeParts = datetimeString.split(" ");
    String[] dateParts = datetimeParts[0].split("/");
    String[] timeParts = datetimeParts[1].split(":");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]) - 1;
    int year = Integer.parseInt(dateParts[2]);
    int hour = Integer.parseInt(timeParts[0]);
    int minute = Integer.parseInt(timeParts[1]);
    Calendar calendar = new GregorianCalendar(year, month, day, hour, minute, 0);
    return new TransitDate(calendar.getTime());
  }

  public boolean onSameDay(TransitDate date2) {

    // Code adapted from https://stackoverflow.com/a/2517824/3200577
    // (User: Michael Borgwardt)

    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    cal1.setTime(date);
    cal2.setTime(date2.getDate());
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
        cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
  }

  public boolean inSameMonth(TransitDate date2) {

    // Code adapted from https://stackoverflow.com/a/2517824/3200577
    // (User: Michael Borgwardt)

    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    cal1.setTime(date);
    cal2.setTime(date2.getDate());
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
        cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
  }

  @Override
  public int compareTo(TransitDate td) {
    if (getDate() == null || td.getDate() == null) {
      return 0;
    }
    return getDate().compareTo(td.getDate());
  }
}
