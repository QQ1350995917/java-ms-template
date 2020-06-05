package pwd.initializr.book.api.admin;

import io.swagger.annotations.Api;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.book.api.admin.vo.BookTableAroundVO;
import pwd.initializr.book.api.admin.vo.BookTableVO;
import pwd.initializr.book.api.admin.vo.BookVO;
import pwd.initializr.book.api.admin.vo.CreateBookInput;
import pwd.initializr.book.business.admin.BookService;
import pwd.initializr.book.business.admin.bo.ArticleAroundBO;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.book.business.admin.bo.BookBO;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.book.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:20
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "图书信息",
    value = "adminBookApi",
    description = "图书信息API"
)
@RestController(value = "adminBookApi")
public class BookController extends AdminController implements BookApi {

  @Autowired
  private BookService bookService;

  @Override
  public void createBook(CreateBookInput input) {
    BookBO bookBO = new BookBO();
    BeanUtils.copyProperties(input, bookBO);
    BookBO newBook = bookService.createBook(bookBO);
    super.outputData(newBook.getId());
  }

  @Override
  public void deleteBooks(Long[] bookIds) {
    Integer integer = bookService.deleteBooks(Arrays.asList(bookIds));
    super.outputData(integer);
  }

  @Override
  public void deleteCancelBooks(Long[] bookIds) {
    Integer integer = bookService.deleteCancelBooks(Arrays.asList(bookIds));
    super.outputData(integer);
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
    ObjectList<ArticleBO> articleBOObjectList = bookService
        .listBookTable(bookId, pageIndex, pageSize);
    ObjectList<BookTableVO> result = new ObjectList<>();
    result.setSize(articleBOObjectList.getSize());
    result.setTotal(articleBOObjectList.getTotal());
    result.setIndex(articleBOObjectList.getIndex());
    for (ArticleBO articleBO : articleBOObjectList.getElements()) {
      BookTableVO bookTableVO = new BookTableVO();
      BeanUtils.copyProperties(articleBO, bookTableVO);
      result.getElements().add(bookTableVO);
    }
    super.outputData(result);
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

  @Override
  public void fetchBooksByRange(PageInput input) {
    ObjectList<BookBO> bookBOObjectList = bookService.listBook(input.getIndex(), input.getSize());

    ObjectList<BookVO> result = new ObjectList<>();
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
  public void recommendBooks(Long[] bookIds) {
    Integer integer = bookService.recommendBooks(Arrays.asList(bookIds));
    super.outputData(integer);
  }

  @Override
  public void recommendCancelBooks(Long[] bookIds) {
    Integer integer = bookService.recommendCancelBooks(Arrays.asList(bookIds));
    super.outputData(integer);
  }

  @Override
  public void updateBook(Long bookId, CreateBookInput input) {
    BookBO bookBO = new BookBO();
    BeanUtils.copyProperties(input, bookBO);
    bookBO.setId(bookId);
    Long aLong = bookService.updateBook(bookBO);
    super.outputData(aLong);
  }

  @Override
  public void visibleBooks(Long[] bookIds) {
    Integer integer = bookService.visibleBooks(Arrays.asList(bookIds));
    super.outputData(integer);
  }

  @Override
  public void visibleCancelBooks(Long[] bookIds) {
    Integer integer = bookService.visibleCancelBooks(Arrays.asList(bookIds));
    super.outputData(integer);
  }
}
