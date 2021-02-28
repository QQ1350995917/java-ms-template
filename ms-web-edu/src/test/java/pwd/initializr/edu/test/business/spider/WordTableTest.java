package pwd.initializr.edu.test.business.spider;

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

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-16 14:07
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class WordTableTest {

  public static void main(String[] args) {




  }

  @Test
  public void table0 () {
    StringBuilder lines = new StringBuilder();
    try (BufferedReader bufferedReader = new BufferedReader(
        new FileReader(new File("/Users/pwd/Documents/edu/word_index.html")))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        lines.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    String html = lines.toString();

    Document document = Jsoup.parse(html);
    Element body = document.body();
    Element edulevel = body.getElementsByClass("edulevel").get(0);
    Elements edulevelDiv = edulevel
        .getElementsByClass("daohangunit zw-shenlan-btn    hvr-float margin-bottom-20");
    for (Element element : edulevelDiv) {
      String id = element.getElementsByTag("a").get(0).attr("data-eid");
      String title = element.getElementsByTag("a").get(0).text();
      System.out.println(id  + " : " + title);
    }
  }

  @Test
  public void table1 () {
    HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
    HashMap<String, String> header = new HashMap<>();
    header.put("Accept", "application/json, text/javascript, */*; q=0.01");
    header.put("Accept-Encoding", "gzip, deflate");
    header.put("Accept-Language", "en-US,en;q=0.5");
    header.put("Connection", "keep-alive");
    header.put("Cookie", SpriderConfig.cookie);
    header.put("Host", "47.92.155.170");
    header.put("Referer",
        "http://47.92.155.170/Web/AiWord/index.html");
    header.put("User-Agent", SpriderConfig.UserAgent);
    header.put("X-Requested-With", "XMLHttpRequest");

    String content = httpXByHttpClient
        .get("http://47.92.155.170/Web/AiWord/ajaxGetWordBook.html?zIndex=0&eid=4028b4816460a6da016460b2548e0004", header,
            null);


    System.out.println(content);


  }

  @Test
  public void table2() {
    HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
    HashMap<String, String> header = new HashMap<>();
    header.put("Accept", "application/json, text/javascript, */*; q=0.01");
    header.put("Accept-Encoding", "gzip, deflate");
    header.put("Accept-Language", "en-US,en;q=0.5");
    header.put("Connection", "keep-alive");
    header.put("Cookie", SpriderConfig.cookie);
    header.put("Host", "47.92.155.170");
    header.put("Referer",
        "http://47.92.155.170/Web/AiWord/index.html");
    header.put("User-Agent", SpriderConfig.UserAgent);
    header.put("X-Requested-With", "XMLHttpRequest");

    String content = httpXByHttpClient
        .get("http://47.92.155.170/Web/AiWord/ajaxGetWordBook.html?zIndex=1&iid=000000006be69f92016beec9afb60b9a", header,
            null);


    System.out.println(content);
  }

}
