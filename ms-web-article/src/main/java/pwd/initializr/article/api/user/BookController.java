package pwd.initializr.article.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.article.api.user.vo.BookListInput;
import pwd.initializr.article.business.user.ArticleService;
import pwd.initializr.article.business.user.BookService;
import pwd.initializr.article.business.user.bo.ArticleBO;
import pwd.initializr.article.business.user.bo.BookBO;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.logger.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-15 10:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "图书信息",
    value = "userBookApi",
    description = "图书信息API"
)
@RestController(value = "userBookApi")
@RequestMapping(value = "/api/user/book")
public class BookController extends UserController implements BookApi {

  @Autowired
  private BookService bookService;
  @Autowired
  private ArticleService articleService;

  @ApiOperation(value = "图书清单")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void fetchBookListByRange(BookListInput input) {
    ObjectList<BookBO> bookBOObjectList = bookService.listBookByRange();
    super.outputData(bookBOObjectList);
  }

  @ApiOperation(value = "图书详情")
  @GetMapping(value = {"/{bookId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void fetchBookDetailByBookId(@PathVariable("bookId") Long input) {
    ObjectList<ArticleBO> articleBOObjectList = articleService.listArticleByBookId(input);
    super.outputData(articleBOObjectList);
  }

  @ApiOperation(value = "图书详情")
  @GetMapping(value = {"/around/{bookId}/{articleId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void fetchBookDetailByBookId(@PathVariable("bookId") Long bookId, @PathVariable("articleId") Long articleId) {
    ObjectList<ArticleBO> articleBOObjectList = articleService
        .aroundArticleByArticleId(bookId, articleId);
    super.outputData(articleBOObjectList);
  }

  @ApiOperation(value = "文章详情")
  @GetMapping(value = {"/{bookId}/{articleId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void fetchArticleDetailByArticleId(@PathVariable("bookId") Long bookId,@PathVariable("articleId") Long articleId) {
    ArticleBO articleBO = articleService.detailArticleByArticleId(bookId, articleId);
    super.outputData(articleBO);
  }
}

