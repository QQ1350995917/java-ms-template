package pwd.initializr.gateway.filter;

import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-09 11:38
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class OAuthFilter implements GlobalFilter, Ordered {

  /**
   * 授权访问用户名
   */
  @Value("${spring.security.user.name}")
  private String securityUserName;
  /**
   * 授权访问密码
   */
  @Value("${spring.security.user.pwd}")
  private String securityUserPassword;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    /**oauth授权*/
    String auth = securityUserName.concat(":").concat(securityUserPassword);
    String encodedAuth = new sun.misc.BASE64Encoder()
        .encode(auth.getBytes(Charset.forName("US-ASCII")));
    //注意Basic后面有空格
    String authHeader = "Basic " + encodedAuth;
    //向headers中放授权信息
    ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
        .header("Authorization", new String[]{authHeader}).build();
    //将现在的request变成change对象
    ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
    return chain.filter(build);
  }

  @Override
  public int getOrder() {
    return 1;
  }
}
