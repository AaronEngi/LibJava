package tyrael.datetime;

import org.junit.Test;

import java.util.Calendar;

import wang.tyrael.datetime.TimeSupport;

public class TimeUtilTest {
    @Test
    public void testAddDay(){
        long startTime = System.currentTimeMillis();
        Calendar calendar = TimeSupport.getDayStartCalendar(startTime);
        System.out.println(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar.getTime());
    }
}
