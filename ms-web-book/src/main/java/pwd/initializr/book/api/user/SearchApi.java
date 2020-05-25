package pwd.initializr.book.api.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pwd.initializr.book.api.user.vo.SearchInput;

/**
 * pwd.initializr.book.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-24 08:05
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RequestMapping(value = "/api/user/search")
public interface SearchApi {

  @ApiOperation(value = "搜索图书/文章")
  @GetMapping(value = {
      ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void search(SearchInput input);

  @ApiOperation(value = "搜索文章")
  @GetMapping(value = {
      "/article"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void searchArticle(pwd.initializr.book.api.admin.vo.SearchInput input);

  @ApiOperation(value = "搜索图书")
  @GetMapping(value = {
      "/book"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void searchBook(pwd.initializr.book.api.admin.vo.SearchInput input);

}
