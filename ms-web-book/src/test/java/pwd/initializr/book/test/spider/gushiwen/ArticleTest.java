package pwd.initializr.book.test.spider.gushiwen;

import java.nio.charset.Charset;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.book.business.admin.ArticleService;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.common.http.Get;

/**
 * pwd.initializr.book.test.spider.gushiwen@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-03 21:29
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

  @Autowired
  private ArticleService articleService;


  public static void main(String[] args) throws Exception {
    new ArticleTest().step0();
  }

  @Test
  public void step0() throws Exception {
    Long bookId = 41L;
    HttpResponse httpResponse = Get.doGet("https://so.gushiwen.org", "/wenyan/guanzhi.aspx", null);
    if (httpResponse.getStatusLine().getStatusCode() == 200) {
      if (httpResponse != null) {
        String result = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf-8"));
        Document document = Jsoup.parse(result);
        Elements typeconts = document.getElementsByClass("typecont");

        for (Element typecont : typeconts) {
          String category = typecont.getElementsByClass("bookMl").get(0).text();
          Elements spans = typecont.getElementsByTag("span");
          for (Element span : spans) {
            Elements a = span.getElementsByTag("a");
            String href = a.attr("href");
            String title = a.text();
            System.out.println(span);
            ArticleBO articleBO = new ArticleBO();
            if (span.textNodes().size() > 1) {
              String authorName = span.textNodes().get(0).toString().replace("(", "")
                  .replace(")", "");
              articleBO.setAuthorName(authorName);
            }
            articleBO.setTitle(title);
            articleBO.setSubTitle(title);
            LinkedHashSet<String> objects = new LinkedHashSet<>();
            objects.add(category);
            articleBO.setLabels(objects);
            if (href.contains("https://so.gushiwen.org")) {
              articleBO.setFromUrl(href);
            } else {
              articleBO.setFromUrl("https://so.gushiwen.org" + href);
            }

            String path = articleBO.getFromUrl().replace("https://so.gushiwen.org", "");
            HttpResponse httpResponseDetail = Get.doGet("https://so.gushiwen.org", path, null);
            if (httpResponseDetail.getStatusLine().getStatusCode() == 200) {
              if (httpResponseDetail != null) {
                Document documentDetail = Jsoup.parse(
                    EntityUtils.toString(httpResponseDetail.getEntity(), Charset.forName("utf-8")));
                String id = path.replace("/shiwenv_", "").replace(".aspx", "");
                Elements sourceDetail = documentDetail.getElementsByClass("source");
                String authorNameDetail = sourceDetail.get(0).getElementsByTag("a").get(1).text();
                articleBO.setAuthorName(authorNameDetail);
                Element elementById = documentDetail.getElementById("contson" + id);
                LinkedList<String> graphs = new LinkedList<>();

                Elements pTag = elementById.getElementsByTag("p");
                if (pTag == null) {
                  List<TextNode> textNodes = elementById.textNodes();
                  for (TextNode textNode : textNodes) {
                    String graph = textNode.toString().replace("\n", "").trim();
                    graphs.add(graph);
                  }
                } else {
                  for (int i = 0; i < pTag.size(); i++) {
                    String graph = pTag.get(i).text().replace("　　", "").trim();
                    graphs.add(graph);
                  }
                }

                articleBO.setParagraphs(graphs);
                Elements elementsByTag = documentDetail.getElementsByClass("tag").get(0)
                    .getElementsByTag("a");
                for (int i = 0; i < elementsByTag.size(); i++) {
                  String text = elementsByTag.get(i).text();
                  articleBO.getLabels().add(text);
                }

                articleService.createArticle(articleBO);
                System.out.println();
              }
            }
          }
        }
      }
    }
  }
}
