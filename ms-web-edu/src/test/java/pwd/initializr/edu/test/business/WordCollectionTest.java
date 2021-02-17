package pwd.initializr.edu.test.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.edu.business.bo.WordCollectionBO;
import pwd.initializr.edu.business.bo.WordTableBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-16 16:08
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class WordCollectionTest {

  @Test
  public void collect() {
    HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
    HashMap<String, String> header = new HashMap<>();
    header.put("Accept", "application/json, text/javascript, */*; q=0.01");
    header.put("Accept-Encoding", "gzip, deflate");
    header.put("Accept-Language", "en-US,en;q=0.5");
    header.put("Connection", "keep-alive");
    header.put("Cookie", SpriderConfig.cookie);
    header.put("Host", "47.92.155.170");
    header.put("Referer", "http://47.92.155.170/Web/AiWord/index.html");
    header.put("User-Agent", SpriderConfig.UserAgent);
    header.put("X-Requested-With", "XMLHttpRequest");

    String content = httpXByHttpClient
        .get("http://47.92.155.170/Web/AiWord/ajaxNewWord/jiaocaiid/000000006be69f92016beeca15d70b9c.html?limit=50&offset=0&order=asc", header,
            null);

    List<WordCollectionBO> wordCollectionBOS = JSON
        .parseObject(content).getObject("rows",new TypeReference<List<WordCollectionBO>>(){});

    System.out.println(content);

  }
}
