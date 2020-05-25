package pwd.initializr.book.api.admin;

import pwd.initializr.book.api.admin.vo.CreateArticleInput;
import pwd.initializr.book.api.admin.vo.SearchInput;

/**
 * pwd.initializr.book.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-23 15:06
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface ArticleApi {

  void createArticle(CreateArticleInput input);

  void deleteArticles(Long[] articleIds);

  void fetchArticleById(Long articleId);

  void fetchArticlesByRange(SearchInput input);

  void updateArticle(Long articleId, CreateArticleInput input);
}
