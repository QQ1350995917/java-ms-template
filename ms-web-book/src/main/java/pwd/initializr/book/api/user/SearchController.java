package pwd.initializr.book.api.user;

import io.swagger.annotations.Api;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.book.api.user.vo.SearchInput;
import pwd.initializr.book.business.remote.SearchClientService;
import pwd.initializr.book.business.remote.bo.SearchResultBO;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.book.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-24 11:03
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "图书/文章信息搜索",
    value = "userSearchApi",
    description = "图书/文章信息搜索API"
)
@RestController(value = "userSearchApi")
public class SearchController extends UserController implements SearchApi {

  @Autowired
  private SearchClientService searchClientService;

  @Override
  public void search(SearchInput input) {
    SearchInput tempSearchInput;
    if (input == null) {
      tempSearchInput = new SearchInput();
    } else {
      tempSearchInput = input;
    }

    Output<ObjectList<SearchResultBO>> objectListOutput = searchClientService
        .search(Arrays.asList(new String[]{"book","article"}),tempSearchInput.getKeyword(), tempSearchInput.getIndex(),
            tempSearchInput.getSize());

    if (objectListOutput.getMeta() != null && (objectListOutput.getMeta().getCode() == HttpStatus.OK
        .value())) {
      super.outputData(objectListOutput.getData());
    } else {
      super.outputData();
    }
  }

  @Override
  public void searchArticle(pwd.initializr.book.api.admin.vo.SearchInput input) {
    pwd.initializr.book.api.admin.vo.SearchInput tempSearchInput;
    if (input == null) {
      tempSearchInput = new pwd.initializr.book.api.admin.vo.SearchInput();
    } else {
      tempSearchInput = input;
    }

    Output<ObjectList<SearchResultBO>> objectListOutput = searchClientService
        .search(Arrays.asList(new String[]{"article"}),tempSearchInput.getKeyword(), tempSearchInput.getIndex(),
            tempSearchInput.getSize());

    if (objectListOutput.getMeta().getCode() == HttpStatus.OK.value()) {
      super.outputData(objectListOutput.getData());
    } else {
      super.outputData(objectListOutput.getData());
    }
  }

  @Override
  public void searchBook(pwd.initializr.book.api.admin.vo.SearchInput input) {
    pwd.initializr.book.api.admin.vo.SearchInput tempSearchInput;
    if (input == null) {
      tempSearchInput = new pwd.initializr.book.api.admin.vo.SearchInput();
    } else {
      tempSearchInput = input;
    }

    Output<ObjectList<SearchResultBO>> objectListOutput = searchClientService
        .search(Arrays.asList(new String[]{"book"}),tempSearchInput.getKeyword(), tempSearchInput.getIndex(),
            tempSearchInput.getSize());

    if (objectListOutput.getMeta().getCode() == HttpStatus.OK.value()) {
      super.outputData(objectListOutput.getData());
    } else {
      super.outputData(objectListOutput.getData());
    }
  }
}
