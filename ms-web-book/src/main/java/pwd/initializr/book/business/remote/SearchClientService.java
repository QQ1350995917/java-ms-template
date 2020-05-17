package pwd.initializr.book.business.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pwd.initializr.book.FeignConfig;
import pwd.initializr.book.rpc.ArticleIntoSearch;
import pwd.initializr.book.rpc.BookIntoSearch;
import pwd.initializr.book.rpc.SearchOutput;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.book.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 11:27
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@FeignClient(value = "search", configuration = FeignConfig.class, fallback = SearchClientServiceFallbackImpl.class)
public interface SearchClientService {

  @PostMapping(value = "/api/robot/book/article", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  String postOrPutArticle(@RequestBody ArticleIntoSearch articleIntoSearch);

  @PostMapping(value = "/api/robot/book/book", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  String postOrPutBook(@RequestBody BookIntoSearch bookIntoSearch);

  @GetMapping(value = "/api/robot/book/search", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  Output<ObjectList<SearchOutput>> search(@RequestParam("keyword") String keyword,
      @RequestParam("index") Integer index, @RequestParam("size") Integer size);

}
