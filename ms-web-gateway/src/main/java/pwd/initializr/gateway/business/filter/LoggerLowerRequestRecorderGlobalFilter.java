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
 * pwd.initializr.gateway.business.filter@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-09 08:53
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "gateway.filter.global.logger.enable", matchIfMissing = false)
public class LoggerLowerRequestRecorderGlobalFilter implements GlobalFilter, Ordered {

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

        // 在 GatewayFilter 之前执行， 此时的request时最初的request
        LoggerRecorderServerHttpRequestDecorator request = new LoggerRecorderServerHttpRequestDecorator(exchange.getRequest());

        // 此时的response时 发送回客户端的 response
        LoggerRecorderServerHttpResponseDecorator response = new LoggerRecorderServerHttpResponseDecorator(exchange.getResponse());

        ServerWebExchange ex = exchange.mutate()
            .request(request)
            .response(response)
            .build();

        return LoggerUtil.recorderOriginalRequest(ex)
            .then(Mono.defer(() -> chain.filter(ex)))
            .then(Mono.defer(() -> finishLog(ex)));
    }

    private Mono<Void> finishLog(ServerWebExchange ex) {
        return LoggerUtil.recorderResponse(ex).doOnSuccess(x -> log.info(LoggerUtil.getLogData(ex) + "\n\n\n"));
    }

    @Override
    public int getOrder() {
        return -99;
    }

}
