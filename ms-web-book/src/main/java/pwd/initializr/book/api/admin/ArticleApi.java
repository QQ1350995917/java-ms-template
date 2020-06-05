package pwd.initializr.book.api.admin;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pwd.initializr.book.api.admin.vo.CreateArticleInput;
import pwd.initializr.common.web.api.vo.PageInput;

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

  @ApiOperation(value = "添加文章")
  @PostMapping(value = {
      ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void createArticle(CreateArticleInput input);

  @ApiOperation(value = "文章删除")
  @DeleteMapping(value = {
      "/delete"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void deleteArticles(@RequestBody Long[] articleIds);

  @ApiOperation(value = "取消文章删除")
  @PutMapping(value = {
      "/delete/cancel"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void deleteCancelArticles(@RequestBody Long[] articleIds);

  @ApiOperation(value = "文章详情")
  @GetMapping(value = {
      "/{articleId}"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void fetchArticleById(Long articleId);

  @ApiOperation(value = "文章清单")
  @GetMapping(value = {
      ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void fetchArticlesByRange(PageInput input);

  @ApiOperation(value = "图书文章")
  @PutMapping(value = {
      "/recommend"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void recommendArticles(@RequestBody Long[] articleIds);

  @ApiOperation(value = "取消文章推荐")
  @DeleteMapping(value = {
      "/recommend/cancel"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void recommendCancelArticles(@RequestBody Long[] articleIds);

  @ApiOperation(value = "文章更新")
  @PutMapping(value = {
      "/{articleId}"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void updateArticle(Long articleId, CreateArticleInput input);

  @ApiOperation(value = "文章可见")
  @PutMapping(value = {
      "/visible"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void visibleArticles(@RequestBody Long[] articleIds);

  @ApiOperation(value = "取消文章可见")
  @DeleteMapping(value = {
      "/visible/cancel"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void visibleCancelArticles(@RequestBody Long[] articleIds);
}
