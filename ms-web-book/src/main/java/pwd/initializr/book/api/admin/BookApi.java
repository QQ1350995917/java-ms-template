package pwd.initializr.book.api.admin;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pwd.initializr.book.api.admin.vo.CreateBookInput;
import pwd.initializr.book.api.admin.vo.SearchInput;
import pwd.initializr.common.web.api.vo.PageInput;

/**
 * pwd.initializr.book.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:15
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RequestMapping(value = "/api/admin/book")
public interface BookApi {


    @ApiOperation(value = "图书清单")
    @GetMapping(value = {
        ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchBooksByRange(PageInput input);


    @ApiOperation(value = "图书详情")
    @GetMapping(value = {
        "/{bookId}"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchBookById(@PathVariable(name = "bookId") Long bookId);

    @ApiOperation(value = "图书目录列表")
    @GetMapping(value = {
        "/{bookId}/{pageIndex}/{pageSize}"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchBookTables(@PathVariable("bookId") Long bookId,
        @PathVariable(value = "pageIndex") Integer pageIndex,
        @PathVariable(value = "pageSize") Integer pageSize);

    @ApiOperation(value = "图书指定章节以及前后章节")
    @GetMapping(value = {
        "/{bookId}/{tableId}"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchBookTablesById(@PathVariable("bookId") Long bookId,
        @PathVariable("tableId") Long articleId);

    @ApiOperation(value = "录入图书信息")
    @PostMapping(value = {""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void createBook(CreateBookInput input);

    @ApiOperation(value = "修改图书信息")
    @PutMapping(value = {"/{bookId}"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void updateBook(@PathVariable("bookId") Long bookId, CreateBookInput input);


    @ApiOperation(value = "删除图书")
    @DeleteMapping(value = {""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void deleteBooks(@RequestBody Long[] bookIds);
}
