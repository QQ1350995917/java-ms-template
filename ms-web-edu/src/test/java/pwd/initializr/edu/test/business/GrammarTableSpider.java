package pwd.initializr.edu.test.business;

import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
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
public class GrammarTableSpider {

  @Test
  public void articleContent() throws Exception {

    StringBuilder lines = new StringBuilder();
    try (BufferedReader bufferedReader = new BufferedReader(
        new FileReader(new File("/Users/pwd/Documents/edu/grammar_table.html")))) {
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
    Elements elementsByClass = body.getElementsByClass("tree tree-bbox");
    Element ul = elementsByClass.get(0).child(0);
    Elements liChildren = ul.children();
    int counter = 0;
    for (Element liChild : liChildren) {
      String title = liChild.getElementsByTag("div").get(0).getElementsByTag("a").get(0).text();

      GrammarTableBO grammarTableBO = new GrammarTableBO();
      grammarTableBO.setPid(0L);
      grammarTableBO.setName(title);
      grammarTableBO.setLeaf(0);
      // TODO store
      counter ++;
      System.out.println("\t" + JSON.toJSON(grammarTableBO));

      Element subUl = liChild.getElementsByTag("ul").get(0);
      Elements subLis = subUl.children();
      for (Element subLi : subLis) {
        String tableName = subLi.getElementsByTag("a").get(0).attr("title");
        GrammarTableBO grammarTableBO1 = new GrammarTableBO();
        grammarTableBO1.setPid(grammarTableBO.getId());
        grammarTableBO1.setName(tableName);
        if (title.equals("初级英语语法口诀汇总")) {
          grammarTableBO1.setLeaf(0);
          // TODO store
          counter ++;
          System.out.println("\t\t"+ JSON.toJSON(grammarTableBO1));
          Elements subsubLis = subLi.getElementsByTag("ul").get(0).children();
          for (Element subsubLi : subsubLis) {
            String href = subsubLi.getElementsByTag("a").get(0).attr("href");
            String subsubTitle = subsubLi.getElementsByTag("a").get(0).attr("title");
            GrammarTableBO grammarTableBO2 = new GrammarTableBO();
            grammarTableBO2.setPid(grammarTableBO1.getId());
            grammarTableBO2.setName(subsubTitle);
            grammarTableBO2.setLeaf(1);
            href = href.substring(href.lastIndexOf("/") + 1,href.length()).split("\\.")[0];
            grammarTableBO2.setData(href);
            // TODO store
            counter ++;
            System.out.println("\t\t\t"+ JSON.toJSON(grammarTableBO2));

          }
        } else {
          String href = subLi.getElementsByTag("a").get(0).attr("href");
          href = href.substring(href.lastIndexOf("/") + 1,href.length()).split("\\.")[0];

          grammarTableBO1.setLeaf(1);
          grammarTableBO1.setData(href);
          // TODO store
          counter ++;
          System.out.println("\t\t"+ JSON.toJSON(grammarTableBO1));
        }
      }
    }
    System.out.println(counter);
  }
}
