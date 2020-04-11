package pwd.initializr.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
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
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.common.web.api.ApiConstant;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.gateway.list.KeyValueList;
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
@Component
public class SessionFilter implements GlobalFilter, Ordered {

  @Value("${account_login_prefix}")
  private String SESSION_PREFIX;

  @Value("${filter_skip_all:true}")
  private Boolean filterSkipAll;

  @Autowired
  private RedisClient redisClient;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    if (filterSkipAll) {
      return chain.filter(exchange);
    }
    ServerHttpRequest request = exchange.getRequest();
    ServerHttpResponse response = exchange.getResponse();
    String method = request.getMethodValue();
    String path = request.getURI().getPath();

    if (KeyValueList.skipToken(path, method)) {
      // 白名单
      return chain.filter(exchange);
    }
    String token = request.getHeaders().getFirst(ApiConstant.HTTP_HEADER_KEY_TOKEN);
    if (token == null) {
      return buildSessionErrorMono(request, response, "请求参数错误");
    }
    String uid;
    try {
      uid = JWT.decode(token).getAudience().get(0);
    } catch (JWTDecodeException e) {
      return buildSessionErrorMono(request, response, "请求参数错误");
    }
    if (uid == null) {
      return buildSessionErrorMono(request, response, "请求参数错误");
    }

    String key = StringUtils.join(new String[]{SESSION_PREFIX, uid});
    String userJson = redisClient.get(key);
    if (userJson != null) {
      String phoneNumber = JSON.parseObject(userJson).getString("phoneNumber");
      JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(phoneNumber))
          .build();
      try {
        jwtVerifier.verify(token);
      } catch (JWTVerificationException e) {
        // Session 获取到 验证失败
        return buildSessionErrorMono(request, response, "请求参数错误");
      }
//      request.getHeaders().add(ApiConstant.HTTP_HEADER_KEY_UID, uid);
      ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
          .header(ApiConstant.HTTP_HEADER_KEY_UID, new String[]{uid}).build();
      //将现在的request 变成 change对象
      ServerWebExchange serverWebExchange = exchange.mutate().request(serverHttpRequest).build();
      // Session 获取到 验证成功
      return chain.filter(serverWebExchange);
    } else {
      // Session 未获取到 超时或者未登录
      return buildSessionErrorMono(request, response, "未登录或登录超时");
    }
  }

  private Mono<Void> buildSessionErrorMono(ServerHttpRequest request, ServerHttpResponse response,
      String message) {
    RequestPath path = request.getPath();
    String url = request.getURI().getPath();
    String redirect;
    if (path.value().contains(KeyValueList.adminPath)) {
      redirect = KeyValueList.adminLogin;
    } else {
      redirect = KeyValueList.userLogin;
    }
    String method = request.getMethodValue();
    response.setStatusCode(HttpStatus.resolve(401));
    HttpHeaders httpHeaders = response.getHeaders();
    httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
    httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    httpHeaders.add("Location", redirect);
    Output<Object> objectOutput = new Output<>(new Meta(HttpStatus.UNAUTHORIZED.value(), message));
    String warning = JSON.toJSONString(objectOutput);
    DataBuffer bodyDataBuffer = response.bufferFactory()
        .wrap(warning.getBytes(StandardCharsets.UTF_8));
//    return response.setComplete();
    return response.writeWith(Mono.just(bodyDataBuffer));
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
