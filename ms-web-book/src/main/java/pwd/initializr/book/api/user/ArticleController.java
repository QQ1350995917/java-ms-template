package pwd.initializr.book.api.user;

import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.book.api.admin.vo.SearchInput;
import pwd.initializr.book.api.user.vo.ArticleVO;
import pwd.initializr.book.business.user.ArticleService;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.common.web.api.user.UserController;

/**
 * pwd.initializr.book.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-23 16:21
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文章信息",
    value = "userArticleApi",
    description = "文章信息API"
)
@RestController(value = "userArticleApi")
public class ArticleController extends UserController implements ArticleApi {

    @Autowired
    private ArticleService articleService;

    @Override
    public void fetchArticles(SearchInput input) {

    }

    @Override
    public void fetchArticle(Long articleId) {
        ArticleBO articleBO = articleService.findArticleByArticleIdInBook(articleId);
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(articleBO, articleVO);
        super.outputData(articleVO);
    }
}
