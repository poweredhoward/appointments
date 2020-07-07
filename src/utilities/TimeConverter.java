package utilities;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter {
    public static ZonedDateTime stringToDateTime(String utcdateTime)  {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");
        LocalDateTime dateTime = LocalDateTime.parse(utcdateTime, df);
        ZonedDateTime utcZoned = ZonedDateTime.of(dateTime, ZoneOffset.UTC);

        ZonedDateTime localZoned = utcZoned.withZoneSameInstant(ZoneId.systemDefault());

        return localZoned;
    }

    public static String dateTimeToString(ZonedDateTime zoned){
        LocalDateTime utc = zoned.ofInstant(zoned.toInstant(), ZoneOffset.UTC).toLocalDateTime();

        Timestamp timestamp = Timestamp.valueOf(utc);
        String s = timestamp.toString();

        return s.substring(0, 19);

    }

}
