package pwd.initializr.book.business.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import pwd.initializr.book.rpc.ArticleIntoSearch;
import pwd.initializr.book.rpc.BookIntoSearch;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;

/**
 * pwd.initializr.book.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 11:30
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class SearchServiceFallbackImpl implements SearchService {

  @Override
  public String postOrPutBook(BookIntoSearch bookIntoSearch) {
    return JSON.toJSONString(new Output(new Meta(504,"error")));
  }

  @Override
  public String postOrPutArticle(ArticleIntoSearch articleIntoSearch) {
    return JSON.toJSONString(new Output(new Meta(504,"error")));
  }
}
