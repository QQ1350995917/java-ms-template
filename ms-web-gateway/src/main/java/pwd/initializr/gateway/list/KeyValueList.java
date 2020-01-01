package pwd.initializr.gateway.list;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * pwd.initializr.gateway.list@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-10 09:30
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
public class KeyValueList extends HashMap implements InitializingBean {

  public static String adminLogin = "/account/api/admin/session";// 管理员登录页面
  public static String adminPath = "/api/admin";// 管理员url中path特征
  public static String userLogin = "/account/api/session";// 用户登录页面

  private static final Map<String,String> withoutTokenUrl = new HashMap<>();

  static {
    withoutTokenUrl.put("/account/api/admin/session","PUT");
    withoutTokenUrl.put("/account/api/session","PUT");
    withoutTokenUrl.put("/account/api/account/phone","PUT");
    withoutTokenUrl.put("/account/api/admin/session/login","POST");
  }

  public static boolean skipToken(String path,String method){
    if (withoutTokenUrl.containsKey(path) && withoutTokenUrl.containsValue(method)) {
      return true;
    }
    return false;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("InitializingBean..");
  }
}
