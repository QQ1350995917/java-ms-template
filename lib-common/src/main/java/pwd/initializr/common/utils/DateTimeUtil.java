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
  public static DateFormat yyyy_MM_dd0HH1mm1ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  public static DateFormat yyyy_MM_dd0HH1mm1ss0SSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
  public static DateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
  public static DateFormat yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
  public static DateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
  public static DateFormat yyyy_MM_dd_HH_mm_ss_SSS = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

  public DateTimeUtil() {

  }

  public static String getCurrent(String formater) {
    DateFormat dateFormat = new SimpleDateFormat(formater);
    return dateFormat.format(new Date());
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
}
