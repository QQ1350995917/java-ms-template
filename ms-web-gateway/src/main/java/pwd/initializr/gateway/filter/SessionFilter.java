package pwd.initializr.gateway.filter;

import com.alibaba.fastjson.JSON;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.common.web.api.ApiConstant;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.filter@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-09 08:52
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SessionFilter implements GlobalFilter, Ordered {

  @Value("${account_login_prefix}")
  private String SESSION_PREFIX;

  @Autowired
  private RedisClient redisClient;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String token = exchange.getRequest().getHeaders().getFirst(ApiConstant.HTTP_HEADER_KEY_TOKEN);
    String uid = exchange.getRequest().getHeaders().getFirst(ApiConstant.HTTP_HEADER_KEY_UID);
    if (token != null && uid != null) {
      String key = StringUtils.join(new String[]{SESSION_PREFIX, String.valueOf(uid)});
      String userJson = redisClient.get(key);
      if (userJson != null) {
        return chain.filter(exchange);
      }
    }
    ServerHttpRequest serverHttpRequest = exchange.getRequest();
    RequestPath path = serverHttpRequest.getPath();
    String redirect;
    // TODO 可配置
    if (path.value().contains("/api/admin")) {
      redirect = "";// TODO 重定向到管理员登录
    } else {
      redirect = "";// TODO 重定向到用户登录
    }
    String method = serverHttpRequest.getMethodValue();
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(HttpStatus.FOUND);
    HttpHeaders httpHeaders = response.getHeaders();
    httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
    httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    httpHeaders.add("Location", redirect);
    Output<Object> objectOutput = new Output<>(
        new Meta(HttpStatus.UNAUTHORIZED.value(), "未登录或登录超时"));
    String warning = JSON.toJSONString(objectOutput);
    DataBuffer bodyDataBuffer = response.bufferFactory()
        .wrap(warning.getBytes(StandardCharsets.UTF_8));
    return response.writeWith(Mono.just(bodyDataBuffer));
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
