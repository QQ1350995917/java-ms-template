package pwd.initializr.book.api.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pwd.initializr.book.api.admin.vo.SearchInput;

/**
 * pwd.initializr.book.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-23 15:05
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RequestMapping(value = "/api/user/article")
public interface ArticleApi {

    @ApiOperation(value = "文章清单")
    @GetMapping(value = {
        ""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchArticles(SearchInput input);

    @ApiOperation(value = "文章详情")
    @GetMapping(value = {
        "/{articleId}"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void fetchArticle(@PathVariable("articleId") Long articleId);
}
