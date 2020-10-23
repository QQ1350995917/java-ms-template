package pwd.initializr.common.http;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

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
public class HttpXByHttpClient extends HttpX {

    protected static HttpXByHttpClientInit httpXByHttpClientInit;

    public HttpXByHttpClient(HttpXConfig httpXConfig) {
        this.init(httpXConfig);
    }

    private void init(HttpXConfig httpXConfig) {
        if (httpXByHttpClientInit == null) {
            synchronized (HttpXByHttpClient.class) {
                if (httpXByHttpClientInit == null) {
                    httpXByHttpClientInit = new HttpXByHttpClientInit(httpXConfig);
                }
            }
        }
    }

    @Override
    public String postJson(String uri,String body) {
        HttpPost httpPost = new HttpPost(uri);
        try (CloseableHttpResponse response = httpXByHttpClientInit.getCloseableHttpClient()
            .execute(httpPost)) {
            StringEntity bodyEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
            bodyEntity.setContentEncoding("UTF-8");
//            bodyEntity.setContentType(ContentType.APPLICATION_JSON.toString());
            httpPost.setEntity(bodyEntity);
            httpPost.setHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            String result = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getJson(String uri,String url) {
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpXByHttpClientInit.getCloseableHttpClient()
            .execute(httpGet)) {
            String result = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
