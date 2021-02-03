package pwd.initializr.common.http;

import java.io.File;
import java.net.URLEncoder;
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

  // 编码中文和空格部分
  public static String urlEncode(String url){
    String resultURL = "";
    try {
      for (int i = 0; i < url.length(); i++) {
        char charAt = url.charAt(i);
        //对汉字和空格处理
        if (isChinese(charAt) || isNbsp(charAt)) {
          String encode = URLEncoder.encode(charAt+"","UTF-8");
          resultURL+=encode;
        }else {
          resultURL+=charAt;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return resultURL;
  }

  // 判断汉字的方法,只要编码在\u4e00到\u9fa5之间的都是汉字
  public static boolean isChinese(char c) {
    return String.valueOf(c).matches("[\u4e00-\u9fa5]");
  }

  // 判断空格
  public static boolean isNbsp(char c){
    return String.valueOf(c).matches("\u00A0|\u0020|\u3000");
  }
}
