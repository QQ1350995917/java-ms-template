package pwd.initializr.gateway.business.filter;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.business.filter@ms-web-gateway
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-01-13 18:05
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class LoggerUtil {

    private final static String LOGGER_RECORDER_BUFFER = "LoggerRecorderGlobalFilter.logger_recorder_buffer";

    private static boolean hasBody(HttpMethod method) {
        //只记录这3种谓词的body
        if (method == HttpMethod.POST || method == HttpMethod.PUT || method == HttpMethod.PATCH) {
            return true;
        }
        return false;
    }

    private static boolean shouldRecordBody(MediaType contentType) {
        String type = contentType.getType();
        String subType = contentType.getSubtype();
        if ("application".equals(type)) {
            return "json".equals(subType) || "x-www-form-urlencoded".equals(subType) || "xml"
                .equals(subType) || "atom+xml".equals(subType) || "rss+xml".equals(subType);
        } else if ("text".equals(type)) {
            return true;
        }
        //form没有记录
        return false;
    }

    private static Mono<Void> doRecordBody(StringBuffer logBuffer, Flux<DataBuffer> body,
        Charset charset) {
        return LoggerDataBufferUtilFix.join(body)
            .doOnNext(wrapper -> {
                logBuffer.append(new String(wrapper.getData(), charset));
                logBuffer.append("\n------------ end ------------\n");
                wrapper.clear();
            }).then();
    }

    private static Charset getMediaTypeCharset(@Nullable MediaType mediaType) {
        if (mediaType != null && mediaType.getCharset() != null) {
            return mediaType.getCharset();
        } else {
            return StandardCharsets.UTF_8;
        }
    }

    public static Mono<Void> recorderOriginalRequest(ServerWebExchange exchange) {
        StringBuffer logBuffer = new StringBuffer("\n---------------------------");
        exchange.getAttributes().put(LOGGER_RECORDER_BUFFER, logBuffer);

        ServerHttpRequest request = exchange.getRequest();
        return recorderRequest(request, request.getURI(),
            logBuffer
                .append("\n").append("origin request")
                .append("\n").append("timestamp:").append(System.currentTimeMillis()).append("\n"));
    }

    public static Mono<Void> recorderRouteRequest(ServerWebExchange exchange) {
        URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);
        StringBuffer logBuffer = exchange.getAttribute(LOGGER_RECORDER_BUFFER);

        return recorderRequest(exchange.getRequest(), requestUrl,
            logBuffer
                .append("\n").append("proxy request")
                .append("\n").append("timestamp:").append(System.currentTimeMillis()).append("\n"));
    }

    private static Mono<Void> recorderRequest(ServerHttpRequest request, URI uri,
        StringBuffer logBuffer) {
        if (uri == null) {
            uri = request.getURI();
        }
        HttpMethod method = request.getMethod();
        HttpHeaders headers = request.getHeaders();
        logBuffer.append(method.toString()).append(' ').append(uri.toString()).append('\n');

        logBuffer.append("------------request header------------\n");
        headers.forEach((name, values) -> {
            values.forEach(value -> {
                logBuffer.append(name).append(":").append(value).append('\n');
            });
        });

        Charset bodyCharset = null;
        if (hasBody(method)) {
            long length = headers.getContentLength();
            if (length <= 0) {
                logBuffer.append("------------without body------------\n");
            } else {
                logBuffer.append("------------body length:").append(length).append(" contentType:");
                MediaType contentType = headers.getContentType();
                if (contentType == null) {
                    logBuffer.append("null，body should not be recode------------\n");
                } else if (!shouldRecordBody(contentType)) {
                    logBuffer.append(contentType.toString())
                        .append("，body should not be recode------------\n");
                } else {
                    bodyCharset = getMediaTypeCharset(contentType);
                    logBuffer.append(contentType.toString()).append("------------\n");
                }
            }
        }

        if (bodyCharset != null) {
            return doRecordBody(logBuffer, request.getBody(), bodyCharset);
        } else {
            logBuffer.append("------------ end ------------\n\n");
            return Mono.empty();
        }
    }

    public static Mono<Void> recorderResponse(ServerWebExchange exchange) {
        LoggerRecorderServerHttpResponseDecorator response = (LoggerRecorderServerHttpResponseDecorator) exchange
            .getResponse();
        StringBuffer logBuffer = exchange.getAttribute(LOGGER_RECORDER_BUFFER);
        logBuffer.append("\n").append("timestamp:").append(System.currentTimeMillis()).append("\n");
        HttpStatus code = response.getStatusCode();
        if (code == null) {
            logBuffer.append("response exception").append("\n------------ end ------------\n\n");
            return Mono.empty();
        }

        logBuffer.append("response：").append(code.value()).append(" ")
            .append(code.getReasonPhrase())
            .append('\n');

        HttpHeaders headers = response.getHeaders();
        logBuffer.append("------------response header------------\n");
        headers.forEach((name, values) -> {
            values.forEach(value -> {
                logBuffer.append(name).append(":").append(value).append('\n');
            });
        });

        Charset bodyCharset = null;
        MediaType contentType = headers.getContentType();
        if (contentType == null) {
            logBuffer
                .append("------------ contentType = null，body should not be recode------------\n");
        } else if (!shouldRecordBody(contentType)) {
            logBuffer.append("------------body should not be recode------------\n");
        } else {
            bodyCharset = getMediaTypeCharset(contentType);
            logBuffer.append("------------body------------\n");
        }

        if (bodyCharset != null) {
            return doRecordBody(logBuffer, response.copy(), bodyCharset);
        } else {
            logBuffer.append("\n------------ end ------------\n\n");
            return Mono.empty();
        }
    }

    public static String getLogData(ServerWebExchange exchange) {
        StringBuffer logBuffer = exchange.getAttribute(LOGGER_RECORDER_BUFFER);
        return logBuffer.toString();
    }
}
