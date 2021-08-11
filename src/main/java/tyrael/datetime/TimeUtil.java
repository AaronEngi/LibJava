package tyrael.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtil {
    public static long ONE_DAY_MILLIS = 3600 * 24 * 1000L;

    public static Calendar getBeijingCalendar() {
        return Calendar.getInstance(getBeijingTimeZone());
    }

    public static TimeZone getBeijingTimeZone() {
        return TimeZone.getTimeZone("GMT+:08:00");
    }

    public static long getTime(int month, int day) {
        Calendar calendar = TimeUtil.getBeijingCalendar();
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
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);

        Calendar calendar = TimeUtil.getBeijingCalendar();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static long getDayStart(long time) {
        Calendar calendar = TimeUtil.getBeijingCalendar();
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
