package pwd.initializr.edu.test.business.spider;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.edu.business.GrammarContentService;
import pwd.initializr.edu.business.GrammarTableService;
import pwd.initializr.edu.business.bo.GrammarContentBO;
import pwd.initializr.edu.business.bo.GrammarTableBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-14 22:38
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GrammarContentServiceTest {

  @Autowired
  private GrammarTableService grammarTableService;

  @Autowired
  private GrammarContentService grammarContentService;


  @Test
  public void articleContent() throws Exception {
    LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
    ScopeBO scopeBO1 = new ScopeBO();
    scopeBO1.setHit("EM");
    scopeBO1.setFieldName("able");
    scopeBO1.setFieldValue(String.valueOf(EntityAble.DISABLE.getNumber()));

    ScopeBO scopeBO2 = new ScopeBO();
    scopeBO2.setHit("EM");
    scopeBO2.setFieldName("leaf");
    scopeBO2.setFieldValue("1");

    scopeBOS.add(scopeBO1);
    scopeBOS.add(scopeBO2);

    PageableQueryResult<GrammarTableBO> grammarTableBOPageableQueryResult = grammarTableService
        .queryAllByCondition(scopeBOS, null, 0L, Long.MAX_VALUE);
    System.out.println(grammarTableBOPageableQueryResult.getTotal());
    List<GrammarTableBO> elements = grammarTableBOPageableQueryResult.getElements();

    for (GrammarTableBO element : elements) {

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


      GrammarContentBO grammarContentBO = new GrammarContentBO();
      grammarContentBO.setId(Long.parseLong(element.getData()));
      grammarContentBO.setText(content);
      grammarContentService.insert(grammarContentBO);

      grammarTableService.ableById(element.getId(), EntityAble.ENABLE);

      Thread.sleep(1000);

    }

  }

}
