package pwd.initializr.gateway.business.filter;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.business.filter@ms-web-gateway
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-01-13 18:08
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "gateway.filter.global.logger.enable", matchIfMissing = false)
public class LoggerHigherRequestRecorderGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest originalRequest = exchange.getRequest();
        URI originalRequestUrl = originalRequest.getURI();

        //只记录http的请求
        String scheme = originalRequestUrl.getScheme();
        if ((!"http".equals(scheme) && !"https".equals(scheme))) {
            return chain.filter(exchange);
        }

        String upgrade = originalRequest.getHeaders().getUpgrade();
        if ("websocket".equalsIgnoreCase(upgrade)) {
            return chain.filter(exchange);
        }

        //在 NettyRoutingFilter 之前执行， 基本上属于倒数第二个过滤器了
        //此时的request是 经过各种转换、转发之后的request
        //对应日志中的 代理请求 部分
        LoggerRecorderServerHttpRequestDecorator request = new LoggerRecorderServerHttpRequestDecorator(
            originalRequest);
        ServerWebExchange ex = exchange.mutate()
            .request(request)
            .build();

        return LoggerUtil.recorderRouteRequest(ex).then(Mono.defer(() -> chain.filter(ex)));
    }

    @Override
    public int getOrder() {
        //在向业务服务转发前执行  NettyRoutingFilter 或 WebClientHttpRoutingFilter
        return Ordered.LOWEST_PRECEDENCE - 10;
    }
}
