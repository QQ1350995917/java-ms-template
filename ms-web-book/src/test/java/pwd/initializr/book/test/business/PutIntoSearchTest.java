package pwd.initializr.book.test.business;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.book.BookApplication;
import pwd.initializr.book.business.remote.SearchClientService;
import pwd.initializr.book.persistence.entity.ArticleEntity;
import pwd.initializr.book.persistence.entity.BookEntity;
import pwd.initializr.search.rpc.RPCSearchHeadVO;
import pwd.initializr.search.rpc.RPCSearchInitInput;
import pwd.initializr.search.rpc.RPCSearchBodyVO;

/**
 * pwd.initializr.book.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 14:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookApplication.class)
public class PutIntoSearchTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private SearchClientService searchClientService;

  @Test
  public void copyArticle() {
    List<ArticleEntity> all = mongoTemplate.findAll(ArticleEntity.class);
    all.forEach(articleEntity -> {
      RPCSearchHeadVO rpcSearchHeadVO = new RPCSearchHeadVO();
      rpcSearchHeadVO.setEsAppId("BOOK-ID");
      rpcSearchHeadVO.setEsAppName("BOOK");
      rpcSearchHeadVO.setEsSecretKey("SECRET-KEY");
      rpcSearchHeadVO.setEsIndex("article");
      List<RPCSearchBodyVO> rpcSearchBodyVOS = new LinkedList<>();
      RPCSearchBodyVO rpcSearchBodyVO = new RPCSearchBodyVO();
      rpcSearchBodyVO.setEsId("id");
      rpcSearchBodyVO.setEsVisibility("VISIBIE");
      rpcSearchBodyVO.setEsTitle(articleEntity.getTitle());
      List<String> strings = Arrays.asList(new String[]{articleEntity.getSubTitle(),articleEntity.getAuthorName(),articleEntity.getSummary()});
      articleEntity.getLabels().forEach(label -> strings.add(label));
      articleEntity.getParagraphs().forEach(paragraph -> strings.add(paragraph));
      rpcSearchBodyVO.setEsLinkTo(
          "http://0.0.0.0:8081/#/book/articleDetail?bookId=" + articleEntity.getBookId()
              + "&articleId=" + articleEntity.getId());
      rpcSearchBodyVO.setEsUpdateTime(new Date());
      rpcSearchBodyVOS.add(rpcSearchBodyVO);
      RPCSearchInitInput rpcSearchInitInput = new RPCSearchInitInput();
      rpcSearchInitInput.setEsHead(rpcSearchHeadVO);
      rpcSearchInitInput.setEsBody(rpcSearchBodyVOS);
      searchClientService.postOrPut(rpcSearchInitInput);
    });
  }

  @Test
  public void copyBook() {
    List<BookEntity> all = mongoTemplate.findAll(BookEntity.class);
    RPCSearchHeadVO rpcSearchHeadVO = new RPCSearchHeadVO();
    rpcSearchHeadVO.setEsAppId("BOOK-ID");
    rpcSearchHeadVO.setEsAppName("BOOK");
    rpcSearchHeadVO.setEsSecretKey("SECRET-KEY");
    rpcSearchHeadVO.setEsIndex("book");
    List<RPCSearchBodyVO> rpcSearchBodyVOS = new LinkedList<>();
    all.forEach(bookEntity -> {
      RPCSearchBodyVO rpcSearchBodyVO = new RPCSearchBodyVO();
      rpcSearchBodyVO.setEsId(bookEntity.getId().toString());
      rpcSearchBodyVO.setEsVisibility("VISIBIE");
      rpcSearchBodyVO.setEsTitle(bookEntity.getTitle());
      List<String> strings = Arrays.asList(
          new String[]{bookEntity.getSubTitle(), bookEntity.getPublisher(),
              bookEntity.getSummary()});
      bookEntity.getLabels().forEach(label -> strings.add(label));
      rpcSearchBodyVO.setEsContent(strings);

      rpcSearchBodyVO.setEsLinkTo(
          "http://0.0.0.0:8081/#/book/articleDetail?bookId=" + bookEntity.getId()
              + "&articleId=" + bookEntity.getId());
      rpcSearchBodyVO.setEsUpdateTime(new Date());
      rpcSearchBodyVOS.add(rpcSearchBodyVO);
    });
    RPCSearchInitInput rpcSearchInitInput = new RPCSearchInitInput();
    rpcSearchInitInput.setEsHead(rpcSearchHeadVO);
    rpcSearchInitInput.setEsBody(rpcSearchBodyVOS);
    searchClientService.postOrPut(rpcSearchInitInput);
  }
}
