package pwd.initializr.common.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.AbstractDocument.Content;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
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
  public String get(String url) {
    return this.get(url, null);
  }

  @Override
  public String get(String url, Map<String, String> params) {
    return this.get(url, null, params);
  }

  @Override
  public String get(String url, Map<String, String> headers, Map<String, String> params) {
    if (params != null) {
      StringBuilder stringBuilder = new StringBuilder();
      for (String key : params.keySet()) {
        String value = params.get(key);
        stringBuilder.append(key);
        stringBuilder.append("=");
        stringBuilder.append(value);
        stringBuilder.append("&");
      }
      stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      url = String.join("?", url, stringBuilder.toString());
    }
    HttpGet httpGet = new HttpGet(url);
    return get(httpGet, headers);
  }

  @Override
  public void getFile(String url, File localFile) {
    this.getFile(url, null, localFile);
  }

  @Override
  public void getFile(String url, Map<String, String> headers, File localFile) {
    this.getFile(url, headers, localFile, 1024 * 128);
  }

  @Override
  public void getFile(String url, Map<String, String> headers, File localFile, int cacheBytes) {
    HttpGet httpGet = new HttpGet(urlEncode(url));
    if (headers != null) {
      for (String key : headers.keySet()) {
        String value = headers.get(key);
        httpGet.setHeader(key, value);
      }
    }

    File parentFile = localFile.getParentFile();
    if (!parentFile.exists()) {
      parentFile.mkdirs();
    }

    try (CloseableHttpResponse response = httpXByHttpClientInit.getCloseableHttpClient()
        .execute(httpGet)) {
      if (response.getStatusLine().getStatusCode() != 200) {
        throw new RuntimeException(String.valueOf(response.getStatusLine().getStatusCode()));
      }
      HttpEntity httpEntity = response.getEntity();
      try (InputStream inputStream = httpEntity.getContent();
          FileOutputStream fileOutputStream = new FileOutputStream(localFile)) {
        byte[] buffer = new byte[cacheBytes];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
          fileOutputStream.write(buffer, 0, length);
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String postFile(String url, String attachmentParamName, File attachment) {
    return this.postFile(url, attachmentParamName, attachment, null);
  }

  @Override
  public String postFile(String url, String attachmentParamName, File attachment,
      Map<String, String> textPlainParams) {
    return this.postFile(url, null, attachmentParamName, attachment, null);
  }

  @Override
  public String postFile(String url, Map<String, String> headers, String attachmentParamName,
      File attachment, Map<String, String> textPlainParams) {
    HttpPost httpPost = new HttpPost(url);
    if (headers != null) {
      for (String key : headers.keySet()) {
        String value = headers.get(key);
        httpPost.setHeader(key, value);
      }
    }

    MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
    if (textPlainParams != null) {
      for (String key : textPlainParams.keySet()) {
        String value = textPlainParams.get(key);
        StringBody stringBody = new StringBody(value,
            ContentType.create("text/plain", Consts.UTF_8));
        multipartEntityBuilder.addPart(key, stringBody);
      }
    }

    try (FileInputStream fileInputStream = new FileInputStream(attachment)) {
      multipartEntityBuilder.addBinaryBody(attachmentParamName, fileInputStream,
          ContentType.create("multipart/form-data", Consts.UTF_8), attachment.getName());
      HttpEntity httpEntity = multipartEntityBuilder.build();
      httpPost.setEntity(httpEntity);
      try (CloseableHttpResponse response = httpXByHttpClientInit.getCloseableHttpClient()
          .execute(httpPost)) {
        String result = EntityUtils.toString(response.getEntity());
        return result;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String postJson(String url, String body) {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json; charset=UTF-8");
    return this.postJson(url, headers, body);
  }

  @Override
  public String postJson(String url, Map<String, String> headers, String body) {
    HttpPost httpPost = new HttpPost(url);
    return requestWithBody(httpPost, headers, body,ContentType.APPLICATION_JSON);
  }

  @Override
  public String postForm(String url, String body) {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    return postForm(url,headers,body);
  }

  @Override
  public String postForm(String url, Map<String, String> headers, String body) {
    HttpPost httpPost = new HttpPost(url);
    return requestWithBody(httpPost, headers, body, ContentType.APPLICATION_FORM_URLENCODED);
  }

  @Override
  public String putJson(String url, String body) {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json; charset=UTF-8");
    return this.putJson(url, headers, body);
  }

  @Override
  public String putJson(String url, Map<String, String> headers, String body) {
    HttpPut httpPut = new HttpPut(url);
    return requestWithBody(httpPut, headers, body, ContentType.APPLICATION_JSON);
  }

  @Override
  public String putForm(String url, String body) {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    return putForm(url,headers,body);
  }

  @Override
  public String putForm(String url, Map<String, String> headers, String body) {
    HttpPut httpPut = new HttpPut(url);
    return requestWithBody(httpPut, headers, body, ContentType.APPLICATION_FORM_URLENCODED);
  }

  private String requestWithBody(HttpEntityEnclosingRequestBase request,
      Map<String, String> headers, String body,ContentType contentType) {
    if (headers != null) {
      for (String key : headers.keySet()) {
        String value = headers.get(key);
        request.setHeader(key, value);
      }
    }
    StringEntity bodyEntity = new StringEntity(body, contentType);
    bodyEntity.setContentType(ContentType.APPLICATION_JSON.toString());
    bodyEntity.setContentEncoding("UTF-8");
    request.setEntity(bodyEntity);
    try (CloseableHttpResponse response = httpXByHttpClientInit.getCloseableHttpClient()
        .execute(request)) {
      String result = EntityUtils.toString(response.getEntity());
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String get(HttpGet httpGet, Map<String, String> headers) {
    if (headers != null) {
      for (String key : headers.keySet()) {
        String value = headers.get(key);
        httpGet.setHeader(key, value);
      }
    }
    try (CloseableHttpResponse response = httpXByHttpClientInit.getCloseableHttpClient()
        .execute(httpGet)) {
      String result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    String url = "？";
    String s = urlEncode(url);
    System.out.println(s);
  }
}
