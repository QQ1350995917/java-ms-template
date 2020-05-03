package pwd.initializr.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class DateTimeUtil {

  private static final String solt = "0";
  public static DateFormat FORMAT_0 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  public DateTimeUtil() {
  }

  public static String date2Str(Date date) {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    return format.format(date);
  }

  public static Long genRandom() {
    StringBuffer time = new StringBuffer();
    time.append(System.currentTimeMillis());
    int random = (int) (Math.random() * 1000.0D);
    if (random < 100) {
      time.append("0");
    }

    if (random < 10) {
      time.append("0");
    }

    return Long.valueOf(Long.parseLong(time.append(random).toString()));
  }

  public static String getCurrentDateTime() {
    return FORMAT_0.format(new Date());
  }

  public static String getCurrentDateTime(DateFormat dateFormat) {
    return dateFormat.format(new Date());
  }

  public static String getCurrentDateTime(String formatter) {
    DateFormat format = new SimpleDateFormat(formatter);
    return format.format(new Date());
  }
}
