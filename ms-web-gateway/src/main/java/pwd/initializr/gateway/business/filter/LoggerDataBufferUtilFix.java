package pwd.initializr.gateway.business.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * pwd.initializr.gateway.business.filter@ms-web-gateway
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-01-13 17:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class LoggerDataBufferUtilFix {
    public static Mono<LoggerDataBufferWrapper> join(Flux<DataBuffer> dataBuffers) {
        return dataBuffers.collectList()
            .filter(list -> !list.isEmpty())
            .map(list -> list.get(0).factory().join(list))
            .map(buf -> {
                InputStream source = buf.asInputStream();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                byte[] buff = new byte[4096];

                try {
                    int n = 0;
                    while ((n = source.read(buff)) != -1) {
                        stream.write(buff, 0, n);
                    }
                } catch (IOException e) {
                    //
                }

                LoggerDataBufferWrapper wrapper = new LoggerDataBufferWrapper(stream.toByteArray(), buf.factory());
                DataBufferUtils.release(buf);   //当前版本的 DataBufferUtils::join 没有这一句

                return wrapper;
            })
            .defaultIfEmpty(new LoggerDataBufferWrapper());
    }
}
