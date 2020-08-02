package pwd.initializr.book.api.user;

import io.swagger.annotations.Api;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.book.api.user.vo.ArticleVO;
import pwd.initializr.book.business.user.ArticleService;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;

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
  public void fetchArticle(Long articleId) {
    ArticleBO articleBO = articleService.findArticleById(articleId);
    ArticleVO articleVO = new ArticleVO();
    BeanUtils.copyProperties(articleBO, articleVO);
    super.outputData(articleVO);
  }

  @Override
  public void fetchArticles(PageInput input) {
    PageableQueryResult<ArticleBO> articleBOPageableQueryResult = articleService
        .listArticleByRange(input.getIndex(), input.getSize());

    PageableQueryResult<ArticleVO> result = new PageableQueryResult<>();

    if (articleBOPageableQueryResult != null) {
      List<ArticleVO> resultVOS = new LinkedList<>();
      articleBOPageableQueryResult.getElements().forEach(articleBO -> {
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(articleBO, articleVO);
        resultVOS.add(articleVO);
      });

      result.setTotal(articleBOPageableQueryResult.getTotal());
      result.setPages(articleBOPageableQueryResult.getPages());
      result.setIndex(articleBOPageableQueryResult.getIndex());
      result.setSize(articleBOPageableQueryResult.getSize());
      result.setElements(resultVOS);
    }
    super.outputData(result);
  }
}
