package pwd.initializr.book.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.book.api.admin.vo.BookListInput;
import pwd.initializr.book.api.admin.vo.CreateBookInput;
import pwd.initializr.book.business.admin.BookService;
import pwd.initializr.book.business.admin.bo.BookBO;
import pwd.initializr.common.web.api.admin.AdminController;
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
@RequestMapping(value = "/api/admin/book")
public class BookController extends AdminController implements BookApi {

  @Autowired
  private BookService bookService;

  @ApiOperation(value = "添加新书")
  @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void createNewBook(CreateBookInput input) {
    BookBO bookBO = new BookBO();
    BeanUtils.copyProperties(input, bookBO);
    BookBO newBook = bookService.createNewBook(bookBO);
    super.outputData(newBook);
  }


  @ApiOperation(value = "图书清单")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void fetchBookByRange(BookListInput input) {
    ObjectList<BookBO> bookBOObjectList = bookService
        .searchBookByRange(input.getWords(), input.getPageIndex(), input.getPageSize());
    super.outputData(bookBOObjectList);
  }
}
