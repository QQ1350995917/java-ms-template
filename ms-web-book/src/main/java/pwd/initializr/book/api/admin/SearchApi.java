package pwd.initializr.book.api.admin;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pwd.initializr.book.api.admin.vo.SearchInput;

/**
 * pwd.initializr.book.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-25 15:23
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RequestMapping(value = "/api/admin/search")
public interface SearchApi {

    @ApiOperation(value = "搜索图书/文章")
    @GetMapping(value = {
        ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void search(SearchInput input);

    @ApiOperation(value = "搜索文章")
    @GetMapping(value = {
        "/article"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void searchArticle(SearchInput input);

    @ApiOperation(value = "搜索图书")
    @GetMapping(value = {
        "/book"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void searchBook(SearchInput input);
}
