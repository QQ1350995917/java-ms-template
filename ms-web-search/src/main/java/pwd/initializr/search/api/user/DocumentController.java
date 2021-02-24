package pwd.initializr.search.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.robot.RobotController;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.api.robot.vo.SearchBody;
import pwd.initializr.search.api.robot.vo.SearchInputVo;
import pwd.initializr.search.business.DocumentService;
import pwd.initializr.search.business.bo.SearchInputBO;
import pwd.initializr.search.rpc.RPCSearchBody;

/**
 * pwd.initializr.search.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 11:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "信息搜索",
    value = "searchApi",
    description = "信息搜索API"
)
@RestController(value = "searchApi")
@RequestMapping(value = "/api/search")
public class DocumentController extends RobotController implements DocumentApi {

  @Autowired
  private DocumentService documentService;

  @ApiOperation(value = "根据关键字搜索")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void search(SearchInputVo input) {
    SearchInputBO searchInputBO = new SearchInputBO();
    BeanUtils.copyProperties(input, searchInputBO);
    outputData(search0(documentService.search(searchInputBO)));
  }

  private PageableQueryResult<SearchBody> search0(PageableQueryResult<? extends RPCSearchBody> search) {
    PageableQueryResult<SearchBody> result = new PageableQueryResult<>();
    if (search != null) {
      result.setSize(search.getSize());
      result.setIndex(search.getIndex());
      result.setTotal(search.getTotal());
      List<SearchBody> elements = new LinkedList<>();
      search.getElements().forEach(articleBO -> {
        SearchBody searchOutputVO = new SearchBody();
        BeanUtils.copyProperties(articleBO, searchOutputVO);
        elements.add(searchOutputVO);
      });
      result.setElements(elements);
    }
    return result;
  }
}
