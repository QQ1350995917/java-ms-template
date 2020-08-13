package pwd.initializr.search.api.robot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.robot.RobotController;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.api.robot.vo.DocumentIntoSearchInputVO;
import pwd.initializr.search.api.robot.vo.SearchBodyVOVO;
import pwd.initializr.search.api.robot.vo.SearchInputVo;
import pwd.initializr.search.business.robot.DocumentService;
import pwd.initializr.search.business.robot.bo.DocumentBO;
import pwd.initializr.search.business.robot.bo.SearchInputBO;
import pwd.initializr.search.rpc.RPCSearchBodyVO;

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
    tags = "信息写入与搜索",
    value = "documentApi",
    description = "信息写入与搜索API"
)
@RestController(value = "documentApi")
@RequestMapping(value = "/api/robot/search")
public class DocumentController extends RobotController implements DocumentApi {

  @Autowired
  private DocumentService documentService;

  @ApiOperation(value = "向ES中写入数据")
  @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void postOrPut(@RequestBody DocumentIntoSearchInputVO input) {
    DocumentBO documentBO = new DocumentBO();
    Optional.ofNullable(input.getEsBody()).orElseGet(LinkedList::new).forEach(documentVO -> {
      BeanUtils.copyProperties(documentVO, documentBO);
      StringBuilder stringBuilder = new StringBuilder();
      Optional.ofNullable(documentVO.getEsContent()).orElseGet(LinkedList::new).forEach(content ->
        stringBuilder.append(content).append(" ")
      );
      documentBO.setEsContent(stringBuilder.toString());
      documentService.create(input.getEsHead().getEsIndex(), documentBO);
    });
    outputData(200);
  }

  @ApiOperation(value = "在ES中搜索")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void search(SearchInputVo input) {
    SearchInputBO searchInputBO = new SearchInputBO();
    BeanUtils.copyProperties(input, searchInputBO);
    outputData(search0(documentService.search(searchInputBO)));
  }


  private PageableQueryResult<SearchBodyVOVO> search0(PageableQueryResult<? extends RPCSearchBodyVO> search) {
    PageableQueryResult<SearchBodyVOVO> result = new PageableQueryResult<>();
    if (search != null) {
      result.setSize(search.getSize());
      result.setIndex(search.getIndex());
      result.setTotal(search.getTotal());
      List<SearchBodyVOVO> elements = new LinkedList<>();
      search.getElements().forEach(articleBO -> {
        SearchBodyVOVO searchOutputVO = new SearchBodyVOVO();
        BeanUtils.copyProperties(articleBO, searchOutputVO);
        elements.add(searchOutputVO);
      });
      result.setElements(elements);
    }
    return result;
  }
}
