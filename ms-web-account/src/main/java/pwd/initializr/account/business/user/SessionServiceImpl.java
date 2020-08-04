package pwd.initializr.account.business.user;

import org.springframework.stereotype.Service;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>服务层逻辑：用户登录系统</h1>
 *
 * date 2019-11-04 15:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {

  @Override
  public SessionCaptchaBO createCaptcha(String cookie) {
    return null;
  }

  @Override
  public String createCookie() {
    return null;
  }

  @Override
  public void createSession(String token, SessionBO sessionBO) {

  }

  @Override
  public Boolean deleteCookie(String cookie) {
    return null;
  }

  @Override
  public Boolean deleteSession(Long userId) {
    return null;
  }

  @Override
  public SessionCookieBO queryCookie(String cookie) {
    return null;
  }

  @Override
  public SessionBO querySession(Long uid) {
    return null;
  }

  @Override
  public void updateCookie(String cookie, SessionCookieBO sessionCookieBO) {

  }

  @Override
  public Boolean updateSession(SessionBO sessionBO) {
    return null;
  }
}
