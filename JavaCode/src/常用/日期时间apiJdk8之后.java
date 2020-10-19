package 常用;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * 相较于jdk8之前的日期时间api（Date，Calendar，SimpleDateFormat）
 * LocalDate / LocalTime / LocalDateTime 等具有以下优点：
 * 不可变性：像日期和时间这样的类应该是不可变的。
 * 无偏移性：Date中的年份是从1900开始的，而月份都从0开始。
 * 统一格式化：格式化只对Date用，Calendar则不行。
 * 此外还有，线程安全，处理闰秒等。
 * 类结构
 * java.time
 * |--- java.time.chrono
 * |--- java.time.format
 * |--- java.time.temporal
 * |--- java.time.zone
 * <p>
 * LocalDate / LocalTime / LocalDateTime
 * 世界完整时间表示：
 * ① Instant        :给机器使用
 * ② OffsetDateTime :以时区偏移量表示时间
 * ③ ZoneDateTime   :指定时区方式表示时间 ZoneId.of("Asia/Shanghai")
 *
 * @author `pjliu`
 * @date 2020/10/10
 */
public class 日期时间apiJdk8之后 {
    public static void main(String[] args) {

    }
}

class LocalDateTest {
    public static void main(String[] args) {
        //now()获取当前
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);

        //of()指定时间:年月日时分秒
        LocalDateTime of
                = LocalDateTime.of(2020, 10, 10, 0, 0, 10);
        System.out.println(of);
        //get()获取数据
        System.out.println(dateTime.getDayOfWeek());
        System.out.println(dateTime.getDayOfMonth());
        System.out.println(dateTime.getDayOfWeek().getValue());
        //with***()和plus**() ：体现了不可变性
    }
}

/**
 * Instant：时间线上的瞬时点。
 */
class InstantTest {
    public static void main(String[] args) {
        //now():获取 UTC 标准时间
        Instant instant = Instant.now();
        System.out.println(instant);
        //添加时间偏移量（时区；中国是东8区 ）
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(+8));
        System.out.println(offsetDateTime);
        //获取时间戳（自1970年1月1日0时0分0秒开始后的毫秒数 ）
        System.out.println(instant.toEpochMilli());
        System.out.println(offsetDateTime.toEpochSecond());
        //通过时间戳获得对应时间 类似于Date.getTime()
        Instant instant1 = Instant.ofEpochMilli(1);
        System.out.println(instant1);
    }

}

/**
 * 预定义的标准格式。如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
 * 本地化相关的格式。如：ofLocalizedDateTime(FormatStyle.LONG)
 * 自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)
 */
class DateFormatterTest {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        //格式化 日期-->字符串
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(formatter.format(now));
        //解析 字符串-->日期
        TemporalAccessor parse = formatter.parse("2020-10-12T10:33:45.745375");
        System.out.println(parse);
        System.out.println("***********************");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println(formatter1.format(now));
        formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(formatter1.format(now));
        //JDK9不需要withZon(ZoneId.systemDefault())
        formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.systemDefault());
        System.out.println(formatter1.format(now));
        //自定义格式化
        DateTimeFormatter formatter2= DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println(formatter2.format(LocalDateTime.now()));
        //自定义解析
        TemporalAccessor parse1 = formatter2.parse("2020-10-12 11:03:03");
        System.out.println(parse1);
    }
}