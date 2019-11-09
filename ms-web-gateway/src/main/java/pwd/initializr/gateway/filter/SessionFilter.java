package pwd.initializr.gateway.filter;

import java.nio.charset.StandardCharsets;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
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

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String jwtToken = exchange.getRequest().getHeaders().getFirst("X-Token");
    if (jwtToken != null) {
      System.out.println("-=-token is " + jwtToken + "-=-");
      return chain.filter(exchange);
    }

    //不合法(响应未登录的异常)
    ServerHttpResponse response = exchange.getResponse();
    //设置headers
    HttpHeaders httpHeaders = response.getHeaders();
    httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
    httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    //设置body
    String warningStr = "未登录或登录超时";
    DataBuffer bodyDataBuffer = response.bufferFactory().wrap(warningStr.getBytes());
    return response.writeWith(Mono.just(bodyDataBuffer));

//    ServerHttpRequest serverHttpRequest = exchange.getRequest();
//    String method = serverHttpRequest.getMethodValue();
//    if(!"POST".equals(method)){
//      ServerHttpResponse response = exchange.getResponse();
//      String message= "非法请求";
//      byte[] bits = message.getBytes(StandardCharsets.UTF_8);
//      DataBuffer buffer = response.bufferFactory().wrap(bits);
//      response.setStatusCode(HttpStatus.UNAUTHORIZED);
//      //指定编码，否则在浏览器中会中文乱码
//      response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
//      return response.writeWith(Mono.just(buffer));
//    }
//    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
