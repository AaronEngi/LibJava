package tyrael.datetime;

import org.junit.jupiter.api.Test;
import wang.tyrael.datetime.TimeSupport;

import java.util.Calendar;

public class TimeUtilTest {
    @Test
    public void testAddDay() {
        long startTime = System.currentTimeMillis();
        Calendar calendar = TimeSupport.getDayStartCalendar(startTime);
        System.out.println(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar.getTime());
    }
}
