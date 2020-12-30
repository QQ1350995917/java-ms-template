package pwd.initializr.gateway.business.filter;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import pwd.initializr.gateway.persistence.dao.SessionDao;

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
@Service
public class SessionFilterServiceImpl implements ApplicationRunner, MessageListener {

  private static final Map<String, String> withoutTokenUrl = new HashMap<>();
  /**
   * 管理员url中path特征
   */
  public static String adminPath = "/api/admin";
  /**
   * 管理员登录页面
   */
  public static String adminLogin = "/account/api/admin/session";
  /**
   * 用户登录页面
   */
  public static String userLogin = "/account/api/session";

  static {
    withoutTokenUrl.put("/account/api/admin/session", "PUT");
    withoutTokenUrl.put("/account/api/session", "PUT");
    withoutTokenUrl.put("/book/api/book", "GET");
  }

  @Resource
  private SessionDao sessionDao;
  private Set<SessionBO> whiteList = new LinkedHashSet<>();
  @Value("${gateway.filter.global.session.token.skip.all:false}")
  private Boolean skipSessionFilter = false;

  public Boolean getSkipSessionFilter() {
    return skipSessionFilter;
  }

  @Override
  public void onMessage(Message message, byte[] bytes) {

  }

  @Override
  public void run(ApplicationArguments args) {

  }

  public boolean skipToken(String path, String method) {
    // TODO 更新时候的并发问题
    return whiteList.stream().filter(sessionBO -> sessionBO.getMethod().equals(method))
        .anyMatch(sessionBO -> Pattern.matches(sessionBO.getExpression(), path));
  }
}
