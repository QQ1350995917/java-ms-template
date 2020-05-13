package pwd.initializr.book.business.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pwd.initializr.book.FeignConfig;
import pwd.initializr.book.rpc.ArticleIntoSearch;
import pwd.initializr.book.rpc.BookIntoSearch;

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
@FeignClient(value = "search", configuration = FeignConfig.class, fallback = SearchServiceFallbackImpl.class)
public interface SearchService {

  @PostMapping(value = "/api/robot/book/book", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  String postOrPutBook(@RequestBody BookIntoSearch bookIntoSearch);

  @PostMapping(value = "/api/robot/book/article", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  String postOrPutArticle(@RequestBody ArticleIntoSearch articleIntoSearch);
}
