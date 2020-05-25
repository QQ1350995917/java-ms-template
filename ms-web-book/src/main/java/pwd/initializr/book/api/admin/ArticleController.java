package pwd.initializr.book.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import pwd.initializr.book.api.admin.vo.CreateArticleInput;
import pwd.initializr.book.api.admin.vo.SearchInput;
import pwd.initializr.book.business.admin.ArticleService;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.book.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-23 15:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文章信息",
    value = "adminArticleApi",
    description = "文章信息API"
)
@RestController(value = "adminArticleApi")
@RequestMapping(value = "/api/admin/article")
public class ArticleController extends AdminController implements ArticleApi {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "文章清单")
    @GetMapping(value = {
        ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void fetchArticlesByRange(@RequestParam SearchInput input) {

    }

    @ApiOperation(value = "文章详情")
    @GetMapping(value = {
        "/{articleId}"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void fetchArticleById(@PathVariable("articleId") Long articleId) {

    }

    @ApiOperation(value = "文章更新")
    @PutMapping(value = {
        "/{articleId}"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void updateArticle(@PathVariable("articleId") Long articleId,
        @RequestBody CreateArticleInput input) {

    }

    @ApiOperation(value = "添加文章")
    @PostMapping(value = {
        ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void createArticle(@RequestBody CreateArticleInput input) {

    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping(value = {
        ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void deleteArticles(@RequestBody Long[] articleIds) {

    }
}
