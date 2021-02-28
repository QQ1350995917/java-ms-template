package pwd.initializr.edu.test.business.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.edu.business.WordTableService;
import pwd.initializr.edu.business.bo.WordTableBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-16 15:27
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordTableServiceTest {

  @Autowired
  private WordTableService wordTableService;

  @org.junit.Test
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
    int index = 0;
    for (Element element : edulevelDiv) {
      String id = element.getElementsByTag("a").get(0).attr("data-eid");
      String title = element.getElementsByTag("a").get(0).text();

      WordTableBO wordTableBO = new WordTableBO();
      wordTableBO.setId(id);
      wordTableBO.setPid("0");
      wordTableBO.setName(title);
      wordTableBO.setSort(String.valueOf(index));
      wordTableBO.setLeaf(0);

      index ++;

      String insert = wordTableService.insert(wordTableBO);
      System.out.println(insert);
    }
  }

  @org.junit.Test
  public void table1 () {

    LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
    ScopeBO scopeBO = new ScopeBO();
    scopeBO.setHit("EM");
    scopeBO.setFieldName("pid");
    scopeBO.setFieldValue("0");

    scopeBOS.add(scopeBO);

    LinkedHashSet<SortBO> sortBOS = new LinkedHashSet<>();
    SortBO sortBO0 = new SortBO();
    sortBO0.setFieldName("sort");
    sortBO0.setSort("ASC");

    sortBOS.add(sortBO0);

    PageableQueryResult<WordTableBO> wordTableBOPageableQueryResult = wordTableService
        .queryAllByCondition(scopeBOS, sortBOS, 0L, Long.MAX_VALUE);
    List<WordTableBO> elements = wordTableBOPageableQueryResult.getElements();

    for (WordTableBO element : elements) {
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
          .get("http://47.92.155.170/Web/AiWord/ajaxGetWordBook.html?zIndex=0&eid=" + element.getId(), header,
              null);

      List<WordTableBO> wordTableBOS = JSON
          .parseObject(content).getObject("info",new TypeReference<List<WordTableBO>>(){});

      int index = 0;
      for (WordTableBO wordTableBO : wordTableBOS) {
        wordTableBO.setPid(element.getId());
        wordTableBO.setName(StringEscapeUtils.unescapeJava(wordTableBO.getBookName()));
        wordTableBO.setLeaf(0);
        wordTableBO.setSort(String.valueOf(index));
        String insert = wordTableService.insert(wordTableBO);
        index ++;

      }

    }
  }

  @Test
  public void table2() {

    LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
    ScopeBO scopeBO = new ScopeBO();
    scopeBO.setHit("ENM");
    scopeBO.setFieldName("pid");
    scopeBO.setFieldValue("0");

    scopeBOS.add(scopeBO);

    LinkedHashSet<SortBO> sortBOS = new LinkedHashSet<>();
    SortBO sortBO = new SortBO();
    sortBO.setFieldName("pid");
    sortBO.setSort("ASC");

    SortBO sortBO0 = new SortBO();
    sortBO0.setFieldName("sort");
    sortBO0.setSort("ASC");

    sortBOS.add(sortBO0);

    PageableQueryResult<WordTableBO> wordTableBOPageableQueryResult = wordTableService
        .queryAllByCondition(scopeBOS, sortBOS, 0L, Long.MAX_VALUE);
    List<WordTableBO> elements = wordTableBOPageableQueryResult.getElements();

    for (WordTableBO element : elements) {
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
          .get("http://47.92.155.170/Web/AiWord/ajaxGetWordBook.html?zIndex=1&iid=" + element.getId(), header,
              null);

      List<WordTableBO> wordTableBOS = JSON
          .parseObject(content).getObject("info",new TypeReference<List<WordTableBO>>(){});

      int index = 0;
      for (WordTableBO wordTableBO : wordTableBOS) {
        wordTableBO.setPid(element.getId());
        wordTableBO.setName(StringEscapeUtils.unescapeJava(wordTableBO.getBookName()));
        wordTableBO.setLeaf(1);
        wordTableBO.setSort(String.valueOf(index));
        String insert = wordTableService.insert(wordTableBO);
        index ++;

      }


    }
  }
}
