package pwd.initializr.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-20 22:40
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class VerifyUtil {

  private static final String phoneNumber20190601 = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
  private static final Pattern phoneNumber20190601Pattern = Pattern.compile(phoneNumber20190601);

  public static Boolean phoneNumber(String phoneNumber) {
    Matcher matcher = phoneNumber20190601Pattern.matcher(phoneNumber);
    if (matcher.find()) {
      return true;
    }
    return false;
  }
}
