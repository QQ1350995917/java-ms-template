package pwd.initializr.book.api.admin;

import io.swagger.annotations.Api;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.book.api.admin.vo.ArticleVO;
import pwd.initializr.book.api.admin.vo.CreateArticleInput;
import pwd.initializr.book.business.admin.ArticleService;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;

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

  @Override
  public void createArticle(@RequestBody CreateArticleInput input) {
    ArticleBO articleBO = new ArticleBO();
    BeanUtils.copyProperties(input, articleBO);
    ArticleBO article = articleService.createArticle(articleBO);
    super.outputData(article.getId());
  }

  @Override
  public void deleteArticles(@RequestBody Long[] articleIds) {
    Integer integer = articleService.deleteArticles(Arrays.asList(articleIds));
    super.outputData(integer.toString());
  }

  @Override
  public void deleteCancelArticles(Long[] articleIds) {
    Integer integer = articleService.deleteCancelArticles(Arrays.asList(articleIds));
    super.outputData(integer.toString());
  }


  @Override
  public void fetchArticleById(@PathVariable("articleId") Long articleId) {
    ArticleBO articleById = articleService.findArticleById(articleId);
    ArticleVO articleVO = new ArticleVO();
    BeanUtils.copyProperties(articleById, articleVO);
    super.outputData(articleVO);
  }

  @Override
  public void fetchArticlesByRange(PageInput input) {
    PageableQueryResult<ArticleBO> articleBOPageableQueryResult = articleService
        .listArticle(input.getIndex(), input.getSize());

    PageableQueryResult<ArticleVO> result = new PageableQueryResult<>();
    if (articleBOPageableQueryResult != null) {
      List<ArticleVO> resultVOS = new LinkedList<>();
      articleBOPageableQueryResult.getElements().forEach(articleBO -> {
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(articleBO, articleVO);
        resultVOS.add(articleVO);
      });
      result.setTotal(articleBOPageableQueryResult.getTotal());
      result.setIndex(articleBOPageableQueryResult.getIndex());
      result.setSize(articleBOPageableQueryResult.getSize());
      result.setElements(resultVOS);
    }
    super.outputData(result);
  }

  @Override
  public void recommendArticles(Long[] articleIds) {
    Integer integer = articleService.recommendArticles(Arrays.asList(articleIds));
    super.outputData(integer.toString());
  }

  @Override
  public void recommendCancelArticles(Long[] articleIds) {
    Integer integer = articleService.recommendCancelArticles(Arrays.asList(articleIds));
    super.outputData(integer.toString());
  }

  @Override
  public void updateArticle(@PathVariable("articleId") Long articleId,
      @RequestBody CreateArticleInput input) {
    ArticleBO articleBO = new ArticleBO();
    BeanUtils.copyProperties(input, articleBO);
    articleBO.setId(articleId);
    Long aLong = articleService.updateArticle(articleBO);
    super.outputData(aLong.toString());
  }

  @Override
  public void visibleArticles(Long[] articleIds) {
    Integer integer = articleService.visibleArticles(Arrays.asList(articleIds));
    super.outputData(integer.toString());
  }

  @Override
  public void visibleCancelArticles(Long[] articleIds) {
    Integer integer = articleService.visibleCancelArticles(Arrays.asList(articleIds));
    super.outputData(integer.toString());
  }
}
