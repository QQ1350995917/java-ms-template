package pwd.initializr.article.test.spider;

import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.article.business.admin.ArticleService;
import pwd.initializr.article.business.admin.BookService;
import pwd.initializr.article.business.admin.bo.ArticleBO;
import pwd.initializr.article.business.admin.bo.BookBO;
import pwd.initializr.article.test.HttpClient;

/**
 * pwd.initializr.article.test.spider@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-21 14:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Novel {

  @Autowired
  private BookService bookService;
  @Autowired
  private ArticleService articleService;

  @Test
  public void book() throws Exception {
    String html = HttpClient.get("");
    Document document = Jsoup.parse(html);
    String title = document.title().split("_")[0];
    BookBO bookBO = new BookBO();
    bookBO.setTitle(title);
    bookBO.setCreateTime(System.currentTimeMillis());
    bookService.createNewBook(bookBO);

    Element body = document.body();
    Element dulist = body.getElementById("dulist");
    Elements aTags = dulist.getElementsByTag("a");
    int current = 0;
    int size = aTags.size();
    for (Element aTag : aTags) {
      String id = aTag.attr("href").split("=")[1];
      String text = aTag.text();
      article(bookBO.getId(),text,id);
      current ++;
      System.out.println(current + " / " + size + " | " + text + " = " + id);
    }
  }

  private void article(Long bookId, String title,String id){
    String html = HttpClient.get("" + id);
    Document document = Jsoup.parse(html);
    Element body = document.body();
    Element content = body.getElementById("tdcontent");
    Elements pTags = content.getElementsByTag("p");

    List<String> ps = new LinkedList<>();
    for (Element pTag : pTags) {
      String text = pTag.text();
      ps.add(text);
    }

    ArticleBO articleBO = new ArticleBO();
    articleBO.setBookId(bookId);
    articleBO.setTitle(title);
    articleBO.setParagraphs(ps);
    articleBO.setStatus(-1);
    articleBO.setCreateTime(System.currentTimeMillis());
    articleService.createNewArticle(articleBO);
  }



}