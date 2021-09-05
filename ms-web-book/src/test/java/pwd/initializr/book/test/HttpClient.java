package pwd.initializr.book.test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
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

  static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
  static ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
    @Override
    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
      HeaderElementIterator it = new BasicHeaderElementIterator
          (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
      while (it.hasNext()) {
        HeaderElement he = it.nextElement();
        String param = he.getName();
        String value = he.getValue();
        if (value != null && param.equalsIgnoreCase
            ("timeout")) {
          return Long.parseLong(value) * 1000;
        }
      }
      return 60 * 1000;//如果没有约定，则默认定义时长为60s
    }
  };
  static CloseableHttpClient httpClient;
  static {
    cm.setMaxTotal(100);//连接池最大并发连接数
    cm.setDefaultMaxPerRoute(100);//单路由最大并发数
    httpClient = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(myStrategy).build();
  }



  // 配置信息
  static RequestConfig requestConfig = RequestConfig.custom()
      // 设置连接超时时间(单位毫秒)
      .setConnectTimeout(600000)
      // 设置请求超时时间(单位毫秒)
      .setConnectionRequestTimeout(600000)
      // socket读写超时时间(单位毫秒)
      .setSocketTimeout(600000)
      // 设置是否允许重定向(默认为true)
      .setRedirectsEnabled(true).build();

  public static String get(String url) {
    String result = null;
    // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
    // 创建Get请求
    HttpGet httpGet = new HttpGet(url);
    // 响应模型
    CloseableHttpResponse response = null;
    try {
      // 将上面的配置信息 运用到这个Get请求里
      httpGet.setConfig(requestConfig);
      // 由客户端执行(发送)Get请求
      System.err.println("=======请求开始=======");
      response = httpClient.execute(httpGet);
      HttpEntity responseEntity = response.getEntity();
      if (response.getStatusLine().getStatusCode() == 200) {
        if (responseEntity != null) {
          result = EntityUtils.toString(responseEntity,Charset.forName("utf-8"));
        }
      }
      System.err.println("=======请求结束=======");
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }


  public static void downloadFile(String url, String filePath, Map<String, String> headMap) {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    try {
      HttpGet httpGet = new HttpGet(url);
      if (headMap != null && headMap.size() > 0) {
        Set<String> keySet = headMap.keySet();
        for (String key : keySet) {
          httpGet.addHeader(key, headMap.get(key));
        }
      }
      CloseableHttpResponse response1 = httpclient.execute(httpGet);
      try {
        System.out.println(response1.getStatusLine());
        HttpEntity httpEntity = response1.getEntity();
        long contentLength = httpEntity.getContentLength();
        InputStream is = httpEntity.getContent();
        // 根据InputStream 下载文件
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int r = 0;
        long totalRead = 0;
        while ((r = is.read(buffer)) > 0) {
          output.write(buffer, 0, r);
          totalRead += r;
        }
        FileOutputStream fos = new FileOutputStream(filePath);
        output.writeTo(fos);
        output.flush();
        output.close();
        fos.close();
        EntityUtils.consume(httpEntity);
      } finally {
        response1.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        httpclient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
