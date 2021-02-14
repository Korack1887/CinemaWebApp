package util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;


public class DateTimeUtil {
   public static ArrayList<String> getDaysForWeek(){
        ArrayList<String> result = new ArrayList<>();
        DateTime currentDay = DateTime.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM");
        for(int i=0;i<7;i++){
            result.add(fmt.print(currentDay.plusDays(i)));
        }
        return result;
    }
}
