package pwd.initializr.edu.test.business.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringEscapeUtils;
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
import pwd.initializr.edu.business.GrammarBookExamService;
import pwd.initializr.edu.business.GrammarBookService;
import pwd.initializr.edu.business.bo.GrammarBookBO;
import pwd.initializr.edu.business.bo.GrammarBookExamBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-15 09:09
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GrammarTableSpiderLevelExam {


  @Autowired
  private GrammarBookService grammarBookService;

  @Autowired
  private GrammarBookExamService grammarBookExamService;

  @Test
  public void exam() throws Exception {

    int rootLoops = 0;
    HashMap<String, Integer> editionCounter = new HashMap<>();
    HashMap<String, Integer> editionUnitCounter = new HashMap<>();
    HashMap<String, Integer> editionUnitIdCounter = new HashMap<>();

    HashMap<String, Integer> repeatEditionCounter = new HashMap<>();
    HashMap<String, Integer> repeatEditionUnitCounter = new HashMap<>();
    HashMap<String, Integer> repeatEditionUnitIdCounter = new HashMap<>();

    LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
    ScopeBO scopeBO2 = new ScopeBO();
    scopeBO2.setHit("EM");
    scopeBO2.setFieldName("leaf");
    scopeBO2.setFieldValue("1");

    scopeBOS.add(scopeBO2);

    LinkedHashSet<SortBO> sortBOS = new LinkedHashSet<>();
    SortBO sortBO0 = new SortBO();
    sortBO0.setFieldName("pid");
    sortBO0.setSort("ASC");

    SortBO sortBO1 = new SortBO();
    sortBO1.setFieldName("id");
    sortBO1.setSort("ASC");

    sortBOS.add(sortBO0);
    sortBOS.add(sortBO1);


    PageableQueryResult<GrammarBookBO> grammarBookBOPageableQueryResult = grammarBookService
        .queryAllByCondition(scopeBOS, sortBOS, 0L, Long.MAX_VALUE);
    if (grammarBookBOPageableQueryResult.getTotal() != 177) {
      throw new RuntimeException("size not 177");
    }

    List<GrammarBookBO> elements = grammarBookBOPageableQueryResult.getElements();
    while (true) {
      for (GrammarBookBO element : elements) {
        HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("Connection", "keep-alive");
        header.put("Cookie", SpriderConfig.cookie);
        header.put("Host", "47.92.155.170");
        header.put("Referer",
            "http://47.92.155.170/Web/AiGrammar/grammar_exam.html&unit_id=" + element.getId());
        header.put("User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:85.0) Gecko/20100101 Firefox/85.0");
        header.put("X-Requested-With", "XMLHttpRequest");

        String content = httpXByHttpClient
            .get("http://47.92.155.170/Web/AiGrammar/grammar_exam.html&unit_id=" + element.getId()
                    + "?unit_id=" + element.getId(), header,
                null);

        List<GrammarBookExamBO> grammarBookExamBOS = JSON.parseObject(content).getObject("info",new TypeReference<List<GrammarBookExamBO>>(){});

        for (GrammarBookExamBO bookExamBO : grammarBookExamBOS) {

          bookExamBO.setQusTitle(StringEscapeUtils.unescapeJava(bookExamBO.getQusTitle()));
          bookExamBO.setCategory(StringEscapeUtils.unescapeJava(bookExamBO.getCategory()));
          bookExamBO.setComments(StringEscapeUtils.unescapeJava(bookExamBO.getComments()));
          bookExamBO.setRemark(StringEscapeUtils.unescapeJava(bookExamBO.getRemark()));
          bookExamBO.setBread(StringEscapeUtils.unescapeJava(bookExamBO.getBread()));

          LinkedHashSet<ScopeBO> examScopeBOS = new LinkedHashSet<>();
          ScopeBO examScopeBO0 = new ScopeBO();
          examScopeBO0.setHit("EM");
          examScopeBO0.setFieldName("edition_id");
          examScopeBO0.setFieldValue(String.valueOf(bookExamBO.getEditionId()));

          ScopeBO examScopeBO1 = new ScopeBO();
          examScopeBO1.setHit("EM");
          examScopeBO1.setFieldName("unit_id");
          examScopeBO1.setFieldValue(String.valueOf(bookExamBO.getUnitId()));

          ScopeBO examScopeBO2 = new ScopeBO();
          examScopeBO2.setHit("EM");
          examScopeBO2.setFieldName("id");
          examScopeBO2.setFieldValue(String.valueOf(bookExamBO.getId()));

          examScopeBOS.add(examScopeBO0);
          examScopeBOS.add(examScopeBO1);
          examScopeBOS.add(examScopeBO2);

          PageableQueryResult<GrammarBookExamBO> grammarBookExamBOPageableQueryResult = grammarBookExamService
              .queryAllByCondition(examScopeBOS, null, 0L, 1L);

          String editionKey = String.valueOf(bookExamBO.getEditionId());

          String editionUnitKey = String.join("-", String.valueOf(bookExamBO.getEditionId()),
              String.valueOf(bookExamBO.getUnitId()));

          String editionUnitIdKey = String.join("-", String.valueOf(bookExamBO.getEditionId()),
              String.valueOf(bookExamBO.getUnitId()), String.valueOf(bookExamBO.getId()));

          if (grammarBookExamBOPageableQueryResult != null
              && grammarBookExamBOPageableQueryResult.getTotal() != null
              && grammarBookExamBOPageableQueryResult.getTotal() > 0) {
            // 记录重复次数
            recorder(repeatEditionCounter, editionKey, 1);
            recorder(repeatEditionUnitCounter, editionUnitKey, 1);
            recorder(repeatEditionUnitIdCounter, editionUnitIdKey, 1);

          } else {
            recorder(editionCounter, editionKey, 1);
            recorder(editionUnitCounter, editionUnitKey, 1);
            recorder(editionUnitIdCounter, editionUnitIdKey, 1);
            grammarBookExamService.insert(bookExamBO);
          }
        }

        printer("一维统计",editionCounter,repeatEditionCounter);
        printer("二维统计",editionUnitCounter,repeatEditionUnitCounter);
        printer("三维统计",editionUnitIdCounter,repeatEditionUnitIdCounter);

        Thread.sleep(1000);
      }
      System.out.println("根循环次数：" + String.valueOf(rootLoops++));
    }

  }

  private static void recorder(Map<String, Integer> map, String key, Integer integer) {
    Integer result = map.get(key);
    if (result == null) {
      result = 1;
    } else {
      result = result + integer;
    }
    map.put(key, result);
  }

  private static void printer(String message, Map<String,Integer> counter, Map<String,Integer> repeatCounter){
    Collection<Integer> values = counter.values();
    Integer editionCounterValue = 0;
    for (Integer value : values) {
      editionCounterValue = value + editionCounterValue;
    }

    Collection<Integer> values1 = repeatCounter.values();
    Integer editionCounterValue1 = 0;
    for (Integer value : values1) {
      editionCounterValue1 = value + editionCounterValue1;
    }

    System.out.println("\t" + message);
    System.out.println("\t\t" + message + "：key总数：" + counter.keySet().size() + ";value总数："
        + editionCounterValue);
    System.out.println("\t\t" + message + "：key总数：" + repeatCounter.keySet().size() + ";value总数："
        + editionCounterValue1);
    System.out.println("\t\t"  + message + "：key重复比率：" + ((float) repeatCounter.keySet().size()
        / (float) counter.keySet().size()) + ";value重复比率：" + ((float) editionCounterValue1
        / (float) editionCounterValue));
  }


}
