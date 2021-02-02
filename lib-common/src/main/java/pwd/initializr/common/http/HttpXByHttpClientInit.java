package pwd.initializr.common.http;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;

/**
 * pwd.initializr.common.http@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 22:54
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class HttpXByHttpClientInit {

    private TrustManager[] trustAllCerts;
    private ConnectionKeepAliveStrategy keepAliveStrategy;
    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    private CloseableHttpClient closeableHttpClient;

    public HttpXByHttpClientInit(HttpXConfig httpXConfig) {
        this.trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
        };

        this.keepAliveStrategy = new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator headerElementIterator = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                return new DefaultConnectionKeepAliveStrategy()
                    .getKeepAliveDuration(response, context);
            }
        };

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, null);
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            // SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                // 正常的SSL连接会验证码所有证书信息
                // .register("https", sslsf)
                //  忽略域名验证码
                .register("https",
                    new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE))
                .build();

            // 初始化连接管理器
            this.poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);
            /*
             * MaxTotal和DefaultMaxPerRoute的区别：
             * 1、MaxTotal是整个池子的大小；
             * 2、DefaultMaxPerRoute是根据连接到的主机对MaxTotal的一个细分；
             * 比如：
             * MaxTotal=400
             * DefaultMaxPerRoute=200
             * 只连接http://www.abc.com时，到这个主机的并发最多只有200；而不是400；
             * 连接到http://www.abc.com和http://www.xyz.com时到每个主机的并发最多只有200；即加起来是400（但不能超过400）；
             * 所以起作用的设置是DefaultMaxPerRoute
             */
            // 同时最多连接数
            this.poolingHttpClientConnectionManager.setMaxTotal(httpXConfig.getMaxTotal());
            // 设置最大路由
            this.poolingHttpClientConnectionManager
                .setDefaultMaxPerRoute(httpXConfig.getDefaultMaxPerRoute());

            RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(httpXConfig.getConnectionRequestTimeout())
                .setConnectTimeout(httpXConfig.getConnectTimeout())
                .setSocketTimeout(httpXConfig.getSocketTimeout())
                .build();

            DefaultHttpRequestRetryHandler defaultHttpRequestRetryHandler = new DefaultHttpRequestRetryHandler(
                httpXConfig.getRetryCount(), httpXConfig.isRetryEnable());

            this.closeableHttpClient = HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setKeepAliveStrategy(keepAliveStrategy)
                .setDefaultRequestConfig(requestConfig)
                // 设置重试次数
                .setRetryHandler(defaultHttpRequestRetryHandler)
                .build();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public CloseableHttpClient getCloseableHttpClient() {
        return closeableHttpClient;
    }
}
