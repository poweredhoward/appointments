package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeConverter {
    public static Calendar stringToCalendar(String date) throws ParseException {
        SimpleDateFormat formattedDate = new SimpleDateFormat("MM-ddd-yyyy hh:mm:ss");
        Date parsedDate = formattedDate.parse(date);
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(parsedDate);

        return calendarDate;

    }

}
