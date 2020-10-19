package 常用;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * Java.util.Date
 *      |---Java.sql.Date
 * @author `pjliu`
 * @date 2020/10/10
 */
public class 日期时间apiJdk8之前 {
}

class DateTest{
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.toString());
        System.out.println(new Date(0));
        java.sql.Date date1 = new java.sql.Date(new Date(0).getTime());
        System.out.println(date1);
    }
}

class SimpleDateFormatTest{
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        Date date = new Date();
        String format = dateFormat.format(date);
        System.out.println(format);
        System.out.println(dateFormat.parse("1911/10/11 下午4:15"));
    }
}

class CalendarTest{
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.get(Calendar.DAY_OF_YEAR));
        System.out.println(instance.get(Calendar.WEEK_OF_YEAR));
    }
}