package pwd.initializr.book.api.user;

import io.swagger.annotations.Api;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.book.api.admin.vo.SearchInput;
import pwd.initializr.book.api.user.vo.BookTableAroundVO;
import pwd.initializr.book.api.user.vo.BookTableVO;
import pwd.initializr.book.api.user.vo.BookVO;
import pwd.initializr.book.business.remote.SearchClientService;
import pwd.initializr.book.business.user.BookService;
import pwd.initializr.book.business.user.bo.ArticleAroundBO;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.book.business.user.bo.BookBO;
import pwd.initializr.book.business.user.bo.SearchInputBO;
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
public class BookController extends UserController implements BookApi {

  @Autowired
  private BookService bookService;

  @Autowired
  private SearchClientService searchClientService;


  @Override
  public void fetchBooksByRange(SearchInput input) {
    SearchInput tempInput = new SearchInput();
    if (input == null) {
      tempInput = input;
    }

    ObjectList<BookBO> bookBOObjectList;
    ObjectList<BookVO> result = new ObjectList<>();
    if (StringUtils.isEmpty(tempInput.getKeyword())) {
      bookBOObjectList = bookService.listBookByRange(tempInput.getIndex(), tempInput.getSize());
    } else {
      SearchInputBO searchInputBO = new SearchInputBO();
      BeanUtils.copyProperties(tempInput,searchInputBO);
      bookBOObjectList = bookService.searchBookByRange(searchInputBO);
    }
    if (bookBOObjectList != null) {
      List<BookVO> resultVOS = new LinkedList<>();
      bookBOObjectList.getElements().forEach(bookBO -> {
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(bookBO, bookVO);
        resultVOS.add(bookVO);
      });
      result.setTotal(bookBOObjectList.getTotal());
      result.setPages(bookBOObjectList.getPages());
      result.setIndex(bookBOObjectList.getIndex());
      result.setSize(bookBOObjectList.getSize());
      result.setElements(resultVOS);
    }
    super.outputData(result);
  }

  @Override
  public void fetchBookById(Long bookId) {
    BookBO bookBO = bookService.findBookById(bookId);
    BookVO bookVO = new BookVO();
    BeanUtils.copyProperties(bookBO, bookVO);
    super.outputData(bookVO);
  }

  @Override
  public void fetchBookTables(Long bookId, Integer pageIndex, Integer pageSize) {
    ObjectList<ArticleBO> articleBOObjectList = bookService.listBookTable(bookId,pageIndex,pageSize);
    ObjectList<BookTableVO> bookTableVOObjectList = new ObjectList<>();
    for (ArticleBO articleBO : articleBOObjectList.getElements()) {
      BookTableVO bookTableVO = new BookTableVO();
      BeanUtils.copyProperties(articleBO, bookTableVO);
      bookTableVOObjectList.getElements().add(bookTableVO);
    }
    super.outputData(bookTableVOObjectList);
  }

  @Override
  public void fetchBookTablesById(Long bookId, Long articleId) {
    ArticleAroundBO articleAroundBO = bookService.listBookTableByAround(bookId, articleId);
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
}

