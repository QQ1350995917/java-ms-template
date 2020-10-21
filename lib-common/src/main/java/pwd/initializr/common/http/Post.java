package pwd.initializr.common.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.protocol.HTTP;

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
public class Post extends Http {

  /**
   * POST 方式请求 参数通过 url 拼接
   *
   * @param host 请求地址
   * @param path 接口路径
   * @param paramsMap 请求参数
   */
  public static HttpResponse doPost(String host, String path, Map<String, String> paramsMap)
      throws IOException {
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(10000)
        .setConnectTimeout(5000)
        .build();
    HttpPost httpPost = new HttpPost(getRequestUrl(host, path, paramsMap));
    httpPost.setConfig(requestConfig);
    httpPost.setHeader(HTTP.CONTENT_TYPE, ContentType.create(ContentType.APPLICATION_FORM_URLENCODED
        .getMimeType(), Consts.UTF_8).toString());

    return httpClient.execute(httpPost);
  }

  /**
   * POST 方式请求 参数通过 Body 传送,JSON 格式
   *
   * @param host 请求地址
   * @param path 接口路径
   * @param jsonParams 请求参数(json 字符串)
   */
  public static HttpResponse doPost(String host, String path, String jsonParams)
      throws IOException {
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(9000)
        .setConnectTimeout(9000)
        .build();
    HttpPost httpPost = new HttpPost(host + path);
    StringEntity stringentity = new StringEntity(jsonParams, ContentType.APPLICATION_JSON);
    httpPost.setEntity(stringentity);
    httpPost.setConfig(requestConfig);
    httpPost.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

    return httpClient.execute(httpPost);
  }

  public static HttpResponse doPost(String host, String path, String jsonParams,int connectionRequestTimeout,int connectTimeout,int socketTimeout)
      throws IOException {
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(connectionRequestTimeout)
        .setConnectTimeout(connectTimeout)
        .setSocketTimeout(socketTimeout)
        .build();
    HttpPost httpPost = new HttpPost(host + path);
    StringEntity stringentity = new StringEntity(jsonParams, ContentType.APPLICATION_JSON);
    httpPost.setEntity(stringentity);
    httpPost.setConfig(requestConfig);
    httpPost.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

    return httpClient.execute(httpPost);
  }


  /**
   * POST 方式请求 文件上传
   *
   * @param host 请求地址
   * @param path 接口路径
   * @param paramsMap 请求参数
   * @param fileInputStream 待上传文件流
   * @param name 文件对应字段名
   * @param fileOriginalName 原始文件名
   */
  public static HttpResponse doPost(String host, String path, Map<String, String> paramsMap,
      InputStream fileInputStream, String name, String fileOriginalName) throws IOException {
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(10000)
        .setConnectTimeout(5000)
        .build();
    HttpPost httpPost = new HttpPost(host + path);
    MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
    // 解决中文文件名乱码问题
    entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
    entityBuilder.setCharset(Consts.UTF_8);
    ContentType contentType = ContentType
        .create(ContentType.TEXT_PLAIN.getMimeType(), Consts.UTF_8);
    if (paramsMap != null) {
      for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
        entityBuilder.addTextBody(entry.getKey(), entry.getValue(), contentType);
      }
    }

    if (fileInputStream != null && name != null && fileOriginalName != null) {
      entityBuilder
          .addBinaryBody(name, fileInputStream, ContentType.DEFAULT_BINARY, fileOriginalName);
    }
    httpPost.setEntity(entityBuilder.build());
    httpPost.setConfig(requestConfig);

    return httpClient.execute(httpPost);
  }


}
