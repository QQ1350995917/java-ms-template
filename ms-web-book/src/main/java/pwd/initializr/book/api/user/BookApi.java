package pwd.initializr.book.api.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pwd.initializr.book.api.user.vo.BookListInput;
import pwd.initializr.book.api.user.vo.SearchInputVO;

/**
 * pwd.initializr.logger.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 20:51
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RequestMapping(value = "/api/user/book")
public interface BookApi {

    @ApiOperation(value = "图书清单")
    @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchBooks(BookListInput input);

    @ApiOperation(value = "图书简介")
    @GetMapping(value = {"/{bookId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchBookSummary(@PathVariable("bookId") Long bookId);

    @ApiOperation(value = "图书目录列表")
    @GetMapping(value = {"/{bookId}/table","/{bookId}/table/{startId}/{pageSize}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchBookTables(@PathVariable("bookId") Long bookId,@PathVariable(value = "startId",required = false) Long startId,@PathVariable(value = "pageSize",required = false) Long pageSize);

    @ApiOperation(value = "图书指定章节前后目录")
    @GetMapping(value = {"/{bookId}/table/around/{tableId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchBookTablesAround(@PathVariable("bookId") Long bookId,@PathVariable("tableId") Long articleId);

    @ApiOperation(value = "文章详情")
    @GetMapping(value = {"/article/{articleId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchArticle(@PathVariable("articleId") Long articleId);

    @ApiOperation(value = "搜索图书/文章")
    @GetMapping(value = {"/search"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void search(SearchInputVO input);

}
