package pwd.initializr.book.api.user;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pwd.initializr.common.web.api.vo.PageInput;

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
@RequestMapping(value = "/api/book")
public interface BookApi {

  @ApiOperation(value = "图书详情")
  @GetMapping(value = {
      "/{bookId}"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void fetchBookById(@PathVariable(name = "bookId") Long bookId);

  @ApiOperation(value = "图书指定章节以及前后章节")
  @GetMapping(value = {
      "/{bookId}/{tableId}"}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void fetchBookTablesById(@PathVariable("bookId") Long bookId,
      @PathVariable("tableId") Long articleId);

  @ApiOperation(value = "图书清单")
  @GetMapping(value = {
      ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void fetchRecommendBooks(
      @ApiParam(name = "scopes", value = "[{\"hit\":\"指定查询方式[EM:精确,ENM:精确非,AL:前后模糊,LL:左模糊,RL:右模糊,SM:范围\",\"fieldName\":\"指定查询字段\",\"fieldValue\":\"指定查询目标\",\"start\":\"范围查询起始值，区间包含\",\"end\":\"范围查询结束值，区间包含\"}]") @RequestParam(value = "scopes", required = false) String scopes,
      @ApiParam(name = "sorts", value = "[{\"fieldName\":\"指定排序字段\",\"sort\":\"[desc|asc]\"}]") @RequestParam(value = "sorts", required = false) String sorts,
      @ApiParam(name = "page", value = "{\"index\":0,\"size\":12}") @RequestParam(value = "page", required = false) String page
  );
}
