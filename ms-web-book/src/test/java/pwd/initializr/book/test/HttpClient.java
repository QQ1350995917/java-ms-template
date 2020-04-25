package pwd.initializr.book.test;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * pwd.initializr.book.test.spider@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-21 14:13
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class HttpClient {

  public static String get(String url) {
    String result = null;
    // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    // 创建Get请求
    HttpGet httpGet = new HttpGet(url);
    // 响应模型
    CloseableHttpResponse response = null;
    try {
      // 配置信息
      RequestConfig requestConfig = RequestConfig.custom()
          // 设置连接超时时间(单位毫秒)
          .setConnectTimeout(600000)
          // 设置请求超时时间(单位毫秒)
          .setConnectionRequestTimeout(600000)
          // socket读写超时时间(单位毫秒)
          .setSocketTimeout(600000)
          // 设置是否允许重定向(默认为true)
          .setRedirectsEnabled(true).build();

      // 将上面的配置信息 运用到这个Get请求里
      httpGet.setConfig(requestConfig);

      // 由客户端执行(发送)Get请求
      response = httpClient.execute(httpGet);


      HttpEntity responseEntity = response.getEntity();
      if (response.getStatusLine().getStatusCode() == 200) {
        if (responseEntity != null) {
          result = EntityUtils.toString(responseEntity,Charset.forName("utf-8"));
        }
      }
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 释放资源
        if (httpClient != null) {
          httpClient.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
