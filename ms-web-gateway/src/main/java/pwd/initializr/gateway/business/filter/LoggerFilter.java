package pwd.initializr.gateway.business.filter;

import com.alibaba.fastjson.JSON;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
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
@ConditionalOnProperty(value = "gateway.filter.global.logger.enable", matchIfMissing = true)
public class LoggerFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest exchangeRequest = exchange.getRequest();
        InetSocketAddress address = exchangeRequest.getRemoteAddress();
        URI url = exchangeRequest.getURI();
        HttpMethod method = exchangeRequest.getMethod();
        HttpHeaders headers = exchangeRequest.getHeaders();
        Flux<DataBuffer> requestBody = exchangeRequest.getBody();
        //读取requestBody传参
        AtomicReference<String> requestBodyReference = new AtomicReference<>("");
        requestBody.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            requestBodyReference.set(charBuffer.toString());
        });
        String requestParams = requestBodyReference.get();
        HashMap<String, Object> requestLogger = new HashMap<>();
        requestLogger.put("address",
            address == null ? "unknown-ip" : address.getHostName() + ":" + address.getPort());
        requestLogger.put("url", url.toString());
        requestLogger.put("method", method == null ? "unknown-method" : method.name());
        requestLogger.put("header", headers);
        requestLogger.put("body", requestParams);
        requestLogger.put("timestamp", System.currentTimeMillis());

        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();

        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        String responseResult = new String(content, Charset.forName("UTF-8"));
                        HashMap<String, Object> responseLogger = new HashMap<>();
                        responseLogger.put("status", this.getStatusCode());
                        responseLogger.put("header", this.getHeaders());
                        responseLogger.put("body", responseResult);
                        responseLogger.put("timestamp", System.currentTimeMillis());

                        HashMap<String, Object> logger = new HashMap<>();
                        logger.put("request", requestLogger);
                        logger.put("response", responseLogger);
                        logger.put("duration",
                            (Long.parseLong(responseLogger.get("timestamp").toString()) - Long
                                .parseLong(requestLogger.get("timestamp").toString())));
                        log.info(JSON.toJSONString(logger));
                        return bufferFactory.wrap(content);
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain
            .filter(exchange.mutate().request(exchangeRequest).response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return -99;
    }
}
