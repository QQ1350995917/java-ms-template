package pwd.initializr.common.http;

import java.io.IOException;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

/**
 * pwd.initializr.common.http@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 22:53
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Get extends Http{

  public static HttpResponse doGet(String host, String path, Map<String, String> paramsMap) throws IOException {
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(1000 * 60 * 10)
        .setConnectTimeout(1000 * 60 * 10)
        .build();
    HttpGet httpGet = new HttpGet(getRequestUrl(host, path, paramsMap));
    httpGet.setConfig(requestConfig);
    httpGet.setHeader(HTTP.CONTENT_TYPE,ContentType.create(ContentType.APPLICATION_FORM_URLENCODED
        .getMimeType(),Consts.UTF_8).toString());

    return httpClient.execute(httpGet);
  }



}
