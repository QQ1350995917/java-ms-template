package pwd.initializr.common.web.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * pwd.initializr.common.web.api@ms-web-initializr
 *
 * <h1>统一日志处理</h1>
 *
 * date 2020-08-13 22:12
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String token = request.getHeader(ApiConstant.HTTP_HEADER_KEY_TOKEN);
    String uid = request.getHeader(ApiConstant.HTTP_HEADER_KEY_UID);
    String aid = request.getHeader(ApiConstant.HTTP_HEADER_KEY_AID);
    String sv = request.getHeader(ApiConstant.HTTP_HEADER_KEY_SERVER_VERSION);
    String cv = request.getHeader(ApiConstant.HTTP_HEADER_KEY_CLIENT_VERSION);
    String os = request.getHeader(ApiConstant.HTTP_HEADER_KEY_OS);
    MDC.put(ApiConstant.HTTP_HEADER_KEY_TOKEN, token);
    MDC.put(ApiConstant.HTTP_HEADER_KEY_UID, uid);
    MDC.put(ApiConstant.HTTP_HEADER_KEY_AID, aid);
    MDC.put(ApiConstant.HTTP_HEADER_KEY_SERVER_VERSION, sv);
    MDC.put(ApiConstant.HTTP_HEADER_KEY_CLIENT_VERSION, cv);
    MDC.put(ApiConstant.HTTP_HEADER_KEY_OS, os);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    MDC.clear();
  }
}
