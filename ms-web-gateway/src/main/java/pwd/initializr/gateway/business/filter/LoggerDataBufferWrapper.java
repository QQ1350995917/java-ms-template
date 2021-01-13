package pwd.initializr.gateway.business.filter;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;

/**
 * pwd.initializr.gateway.business.filter@ms-web-gateway
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-01-13 17:55
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class LoggerDataBufferWrapper {
    private byte[] data;
    private DataBufferFactory factory;

    public LoggerDataBufferWrapper() {
    }

    public LoggerDataBufferWrapper(byte[] data, DataBufferFactory factory) {
        this.data = data;
        this.factory = factory;
    }

    public byte[] getData() {
        return data;
    }

    public DataBufferFactory getFactory() {
        return factory;
    }

    public DataBuffer newDataBuffer() {
        if (factory == null) {
            return null;
        }

        return factory.wrap(data);
    }

    public Boolean clear() {
        data = null;
        factory = null;

        return Boolean.TRUE;
    }
}
