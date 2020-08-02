package pwd.initializr.common.web.api;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * pwd.initializr.common.web.api@ms-web-initializr
 *
 * <h1>国际化配置</h1>
 *
 * date 2019-09-14 15:18
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ApiProperties {

  public static Map<String, ResourceBundle> apiBundles = new HashMap();
  public static Map<String, ResourceBundle> logBundles = new HashMap();

  static {
    apiBundles.put("zh_CN", ResourceBundle.getBundle("configures.lang.apiCode", Locale.CHINA));
    apiBundles.put("en", ResourceBundle.getBundle("configures.lang.apiCode", Locale.ENGLISH));
    logBundles.put("zh_CN", ResourceBundle.getBundle("configures.lang.logCode", Locale.CHINA));
    logBundles.put("en", ResourceBundle.getBundle("configures.lang.logCode", Locale.ENGLISH));
  }

  public ApiProperties() {
  }
}
