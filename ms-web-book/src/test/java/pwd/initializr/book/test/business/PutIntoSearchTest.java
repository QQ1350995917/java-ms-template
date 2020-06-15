package pwd.initializr.book.test.business;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang.StringUtils;
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
import pwd.initializr.search.rpc.RPCSearchBodyVO;
import pwd.initializr.search.rpc.RPCSearchHeadVO;
import pwd.initializr.search.rpc.RPCSearchInitInput;

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
  public void putArticleIntoSearch() {
    List<ArticleEntity> all = mongoTemplate.findAll(ArticleEntity.class);
    all.forEach(articleEntity -> {
      RPCSearchHeadVO rpcSearchHeadVO = new RPCSearchHeadVO();
      rpcSearchHeadVO.setEsAppId("BOOK-ID");
      rpcSearchHeadVO.setEsAppName("BOOK");
      rpcSearchHeadVO.setEsSecretKey("SECRET-KEY");
      rpcSearchHeadVO.setEsIndex("article");
      List<RPCSearchBodyVO> rpcSearchBodyVOS = new LinkedList<>();
      RPCSearchBodyVO rpcSearchBodyVO = new RPCSearchBodyVO();
      rpcSearchBodyVO.setEsId(articleEntity.getId().toString());
      rpcSearchBodyVO.setEsVisibility("VISIBIE");
      rpcSearchBodyVO.setEsTitle(articleEntity.getTitle());
      List<String> content = new LinkedList<>();
      if (StringUtils.isNotBlank(articleEntity.getSubTitle())) {
        //content.add(articleEntity.getSubTitle());
      }
      if (StringUtils.isNotBlank(articleEntity.getAuthorName())) {
        content.add(articleEntity.getAuthorName());
      }
      if (StringUtils.isNotBlank(articleEntity.getSummary())) {
        content.add(articleEntity.getSummary());
      }
      Optional.ofNullable(articleEntity.getLabels()).orElseGet(LinkedHashSet::new)
          .forEach(label -> content.add(label));
      Optional.ofNullable(articleEntity.getParagraphs()).orElseGet(LinkedList::new)
          .forEach(paragraph -> content.add(paragraph));
      rpcSearchBodyVO.setEsContent(content);
      rpcSearchBodyVO.setEsLinkTo(
          "http://0.0.0.0:8081/#/book/articleDetail?bookId=" + articleEntity.getBookId()
              + "&articleId=" + articleEntity.getId());
      rpcSearchBodyVO.setEsUpdateTime("2020-05-05");
      rpcSearchBodyVOS.add(rpcSearchBodyVO);
      RPCSearchInitInput rpcSearchInitInput = new RPCSearchInitInput();
      rpcSearchInitInput.setEsHead(rpcSearchHeadVO);
      rpcSearchInitInput.setEsBody(rpcSearchBodyVOS);
      searchClientService.postOrPut(rpcSearchInitInput);
    });
  }

  @Test
  public void putBookIntoSearch() {
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
      List<String> content = new LinkedList<>();
      if (StringUtils.isNotBlank(bookEntity.getSubTitle())) {
        //content.add(bookEntity.getSubTitle());
      }
      if (StringUtils.isNotBlank(bookEntity.getPublisher())) {
        content.add(bookEntity.getPublisher());
      }
      if (StringUtils.isNotBlank(bookEntity.getSummary())) {
        content.add(bookEntity.getSummary());
      }
      Optional.ofNullable(bookEntity.getLabels()).orElseGet(LinkedHashSet::new)
          .forEach(label -> content.add(label));
      rpcSearchBodyVO.setEsContent(content);

      rpcSearchBodyVO.setEsLinkTo(
          "http://0.0.0.0:8081/#/book/articleDetail?bookId=" + bookEntity.getId()
              + "&articleId=" + bookEntity.getId());
      rpcSearchBodyVO.setEsUpdateTime("2020-05-05");
      rpcSearchBodyVOS.add(rpcSearchBodyVO);
    });
    RPCSearchInitInput rpcSearchInitInput = new RPCSearchInitInput();
    rpcSearchInitInput.setEsHead(rpcSearchHeadVO);
    rpcSearchInitInput.setEsBody(rpcSearchBodyVOS);
    searchClientService.postOrPut(rpcSearchInitInput);
  }
}
