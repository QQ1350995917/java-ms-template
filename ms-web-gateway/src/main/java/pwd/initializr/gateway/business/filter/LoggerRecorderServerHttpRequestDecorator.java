package pwd.initializr.gateway.business.filter;

import java.util.LinkedList;
import java.util.List;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.business.filter@ms-web-gateway
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-01-13 17:52
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class LoggerRecorderServerHttpRequestDecorator extends ServerHttpRequestDecorator {
    private final List<DataBuffer> dataBuffers = new LinkedList<>();
    private boolean bufferCached = false;
    private Mono<Void> progress = null;

    public LoggerRecorderServerHttpRequestDecorator(ServerHttpRequest delegate) {
        super(delegate);
    }

    @Override
    public Flux<DataBuffer> getBody() {
        synchronized (dataBuffers) {
            if (bufferCached) {
                return copy();
            }

            if (progress == null) {
                progress = cache();
            }

            return progress.thenMany(Flux.defer(this::copy));
        }
    }

    private Flux<DataBuffer> copy() {
        return Flux.fromIterable(dataBuffers)
            .map(buf -> buf.factory().wrap(buf.asByteBuffer()));
    }

    private Mono<Void> cache() {
        return super.getBody()
            .map(dataBuffers::add)
            .then(Mono.defer(()-> {
                bufferCached = true;
                progress = null;

                return Mono.empty();
            }));
    }
}
