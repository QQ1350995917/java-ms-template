package pwd.initializr.book.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.book.api.admin.vo.CreateBookInput;
import pwd.initializr.book.api.admin.vo.SearchInput;
import pwd.initializr.book.business.admin.BookService;
import pwd.initializr.book.business.admin.bo.BookBO;
import pwd.initializr.common.web.api.admin.AdminController;

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


    @ApiOperation(value = "图书清单")
    @GetMapping(value = {
        ""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void fetchBooksByRange(@RequestParam SearchInput input) {

    }

    @ApiOperation(value = "图书详情")
    @GetMapping(value = {
        "/{bookId}"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void fetchBookById(@PathVariable(name = "bookId", required = true) Long bookId) {

    }

    @ApiOperation(value = "图书目录列表")
    @GetMapping(value = {
        "/{bookId}/{startId}/{pageSize}"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void fetchBookTables(@PathVariable("bookId") Long bookId,
        @PathVariable(value = "startId", required = false) Long startId,
        @PathVariable(value = "pageSize", required = false) Long pageSize) {

    }

    @ApiOperation(value = "图书指定章节以及前后目录")
    @GetMapping(value = {
        "/{bookId}/{tableId}"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void fetchBookTableById(@PathVariable("bookId") Long bookId,
        @PathVariable("tableId") Long articleId) {

    }


    @ApiOperation(value = "图书更新")
    @PutMapping(value = {
        "/{bookId}"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void updateBook(@PathVariable(name = "bookId", required = true) Long bookId,
        @RequestBody CreateBookInput input) {

    }

    @ApiOperation(value = "添加图书")
    @PostMapping(value = {
        ""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void createBook(@RequestBody CreateBookInput input) {
        BookBO bookBO = new BookBO();
        BeanUtils.copyProperties(input, bookBO);
        BookBO newBook = bookService.createNewBook(bookBO);
        super.outputData(newBook.getId());
    }

    @ApiOperation(value = "删除图书")
    @DeleteMapping(value = {
        ""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void deleteBooks(@RequestBody Long[] bookIds) {

    }
}
