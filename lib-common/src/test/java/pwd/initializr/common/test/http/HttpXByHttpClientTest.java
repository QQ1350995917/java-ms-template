package pwd.initializr.common.test.http;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import pwd.initializr.common.http.HttpX;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;

/**
 * pwd.initializr.common.test.http@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-23 17:54
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class HttpXByHttpClientTest {

  HttpX httpX = new HttpXByHttpClient(new HttpXConfig());

  @Test
  public void testGet() {
    Map<String, String> headers = new HashMap<>();
    headers.put("User-Agent",
        "Mozilla/5.0 (ShellOnWindows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
    Map<String, String> params = new HashMap<>();
    params.put("id", "123456");
    params.put("language", "中文");
    httpX.get("http://127.0.0.1:11260/api/robot/os", headers, params);
  }

  @Test
  public void testPutJson() {
    Map<String, String> headers = new HashMap<>();
    headers.put("User-Agent",
        "Mozilla/5.0 (ShellOnWindows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
    HashMap<String, String> stringStringHashMap = new HashMap<>();
    stringStringHashMap.put("id", System.currentTimeMillis() + "");
    String jsonString = JSON.toJSONString(stringStringHashMap);
    httpX.putJson("http://127.0.0.1:11260/api/robot/os", headers, jsonString);
  }
}
