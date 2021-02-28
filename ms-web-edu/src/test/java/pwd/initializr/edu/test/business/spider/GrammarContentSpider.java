package pwd.initializr.edu.test.business.spider;

import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.edu.business.bo.GrammarTableBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-14 21:40
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class GrammarContentSpider {

  @Test
  public void articleContent() throws Exception {
    HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
    HashMap<String, String> header = new HashMap<>();
    header.put("Accept", "application/json, text/javascript, */*; q=0.01");
    header.put("Accept-Encoding", "gzip, deflate");
    header.put("Accept-Language", "zh-CN,zh;q=0.9");
    header.put("Connection", "keep-alive");
    header.put("Cookie", SpriderConfig.cookie);
    header.put("Host", "47.92.155.170");
    header.put("Referer",
        "http://47.92.155.170/Web/AiClassroom/znyf/grmmarId/308.html");
    header.put("User-Agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");
    header.put("X-Requested-With", "XMLHttpRequest");

    String content = httpXByHttpClient
        .get("http://47.92.155.170/Web/AiClassroom/znyf/grmmarId/9.html", header,
            null);

    Document document = Jsoup.parse(content);
    Element body = document.body();
    Element contentElement = body.getElementsByClass("g-kewenright-bbox  col-md-8  my_scroll my_scroll_no_radius").get(0);
    content = contentElement.html();

    System.out.println(content);
  }
}
