package tyrael.datetime;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeSupport {
    public static long ONE_DAY_MILLIS = 3600 * 24 * 1000L;

    public static Calendar getBeijingCalendar() {
        return Calendar.getInstance(getBeijingTimeZone());
    }

    public static TimeZone getBeijingTimeZone() {
        return TimeZone.getTimeZone("GMT+:08:00");
    }

    public static long getTime(int month, int day) {
        Calendar calendar = TimeSupport.getBeijingCalendar();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long daysFromNow(int diff) {
        return System.currentTimeMillis() + diff * 24 * 60 * 60 * 1000L;
//        Calendar calendar = TimeSupport.getBeijingCalendar();
//        calendar.add(Calendar.DAY_OF_MONTH, diff);
//        return calendar.getTimeInMillis();
    }

    public static Calendar getDayStartCalendar(long time) {
        Calendar calendar = TimeSupport.getBeijingCalendar();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static long getDayStart(long time) {
        Calendar calendar = TimeSupport.getBeijingCalendar();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static int diffDays(long startTime, long endTime) {
        return (int) ((endTime - startTime) / ONE_DAY_MILLIS);
    }
}
