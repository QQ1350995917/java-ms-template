package pwd.initializr.common.http;

import java.util.Map;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * pwd.initializr.common.http@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 22:59
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Http {

  protected static volatile HttpClient httpClient = HttpClients.createDefault();

  /**
   * 获取完整请求地址(包含参数) 参数拼接在 url 中
   *
   * @param host 请求地址
   * @param path 接口路径
   * @param paramsMap 请求参数
   */
  protected static String getRequestUrl(String host, String path, Map<String, String> paramsMap) {
    StringBuilder reqUrl = new StringBuilder(host).append(path);
    if (paramsMap != null && !paramsMap.isEmpty()) {
      StringBuilder params = new StringBuilder();
      for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
        params.append("&" + entry.getKey() + "=" + entry.getValue());
      }
      String paramConnector = "?";
      if (!host.contains(paramConnector) && !path.contains(paramConnector)) {
        reqUrl.append(paramConnector);
        reqUrl.append(params.toString().substring(1));
      } else {
        reqUrl.append(params.toString());
      }
    }

    return reqUrl.toString();
  }
}
