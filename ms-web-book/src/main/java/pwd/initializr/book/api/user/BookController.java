package pwd.initializr.book.api.user;

import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.book.api.user.vo.ArticleVO;
import pwd.initializr.book.api.user.vo.BookListInput;
import pwd.initializr.book.api.user.vo.BookTableAroundVO;
import pwd.initializr.book.api.user.vo.BookTableVO;
import pwd.initializr.book.api.user.vo.BookVO;
import pwd.initializr.book.api.user.vo.SearchInputVO;
import pwd.initializr.book.business.remote.SearchClientService;
import pwd.initializr.book.business.user.ArticleService;
import pwd.initializr.book.business.user.BookService;
import pwd.initializr.book.business.user.bo.ArticleAroundBO;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.book.business.user.bo.BookBO;
import pwd.initializr.book.rpc.SearchOutput;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.api.vo.Output;
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
public class BookController extends UserController implements BookApi {

  @Autowired
  private BookService bookService;
  @Autowired
  private ArticleService articleService;
  @Autowired
  private SearchClientService searchClientService;

  @Override
  public void fetchBooks(BookListInput input) {
    ObjectList<BookBO> bookBOObjectList = bookService.listBookByRange();
    ObjectList<BookVO> bookVOObjectList = new ObjectList<>();
    for (BookBO bookBO : bookBOObjectList.getElements()) {
      BookVO bookVO = new BookVO();
      BeanUtils.copyProperties(bookBO, bookVO);
      bookVOObjectList.getElements().add(bookVO);
    }
    super.outputData(bookVOObjectList);
  }

  @Override
  public void fetchBookSummary(Long bookId) {
    BookBO bookBO = bookService.findBookById(bookId);
    BookVO bookVO = new BookVO();
    BeanUtils.copyProperties(bookBO, bookVO);
    super.outputData(bookVO);
  }

  @Override
  public void fetchBookTables(Long bookId, Long startId, Long pageSize) {
    ObjectList<ArticleBO> articleBOObjectList = articleService.listTablesInBook(bookId);
    ObjectList<BookTableVO> bookTableVOObjectList = new ObjectList<>();
    for (ArticleBO articleBO : articleBOObjectList.getElements()) {
      BookTableVO bookTableVO = new BookTableVO();
      BeanUtils.copyProperties(articleBO, bookTableVO);
      bookTableVOObjectList.getElements().add(bookTableVO);
    }
    super.outputData(bookTableVOObjectList);
  }

  @Override
  public void fetchBookTablesAround(Long bookId, Long tableId) {
    ArticleAroundBO articleAroundBO = articleService.listTablesAroundInBook(bookId, tableId);
    ArticleBO pre = articleAroundBO.getPre();
    ArticleBO next = articleAroundBO.getNext();

    BookTableAroundVO bookTableAroundVO = new BookTableAroundVO();
    if (pre != null) {
      BookTableVO preBookTableVO = new BookTableVO();
      BeanUtils.copyProperties(pre, preBookTableVO);
      bookTableAroundVO.setPre(preBookTableVO);
    }
    if (next != null) {
      BookTableVO nextBookTableVO = new BookTableVO();
      BeanUtils.copyProperties(next, nextBookTableVO);
      bookTableAroundVO.setNext(nextBookTableVO);
    }

    super.outputData(bookTableAroundVO);
  }

  @Override
  public void fetchArticle(Long articleId) {
    ArticleBO articleBO = articleService.findArticleByArticleIdInBook(articleId);
    ArticleVO articleVO = new ArticleVO();
    BeanUtils.copyProperties(articleBO, articleVO);
    super.outputData(articleVO);
  }

  @Override
  public void search(SearchInputVO input) {
    Output<ObjectList<SearchOutput>> search = searchClientService
        .search(input.getKeyword(), input.getIndex(), input.getSize());
    super.outputData(search.getData());
  }
}

