package pwd.initializr.common.http;

import java.io.File;
import java.util.Map;

/**
 * pwd.initializr.common.http@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-23 18:11
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class HttpX {


  public abstract String get(String url);

  public abstract String get(String url, Map<String, String> params);

  public abstract String get(String url, Map<String, String> headers, Map<String, String> params);

  public abstract void getFile(String url, File localFile);

  public abstract void getFile(String url, Map<String, String> headers, File localFile);

  public abstract void getFile(String url, Map<String, String> headers, File localFile,
      int cacheBytes);

  public abstract String postFile(String url, String attachmentParamName, File attachment);

  public abstract String postFile(String url, String attachmentParamName, File attachment,
      Map<String, String> textPlainParams);

  public abstract String postFile(String url, Map<String, String> headers,
      String attachmentParamName, File attachment, Map<String, String> textPlainParams);

  public abstract String postJson(String url, String body);

  public abstract String postJson(String url, Map<String, String> headers, String body);

  public abstract String putJson(String url, String body);

  public abstract String putJson(String url, Map<String, String> headers, String body);
}
