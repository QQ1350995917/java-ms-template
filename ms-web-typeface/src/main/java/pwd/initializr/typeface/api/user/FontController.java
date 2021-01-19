package pwd.initializr.typeface.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.typeface.api.user.vo.FontListOutput;
import pwd.initializr.typeface.api.user.vo.FontVO;
import pwd.initializr.typeface.business.FontService;
import pwd.initializr.typeface.business.bo.FontBO;

/**
 * pwd.initializr.typeface.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 20:58
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "字体",
    value = "字体Api",
    description = "字体API"
)
@RestController(value = "fontApi")
@RequestMapping(value = "/api/font")
public class FontController extends UserController implements FontApi {

  @Autowired
  private FontService fontService;

  @ApiOperation(value = "字体列表查询")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listFont(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<FontBO> queryResult = fontService
        .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<FontVO> result = new PageOutput<>();
    queryResult.getElements().forEach(fontBO -> {
      FontVO fontVO = new FontVO();
      BeanUtils.copyProperties(fontBO, fontVO);
      result.getElements().add(fontVO);
    });
    result.setTotal(queryResult.getTotal());
    result.setIndex(queryResult.getIndex());
    result.setSize(queryResult.getSize());
    outputData(result);
  }

  @ApiOperation(value = "字体详情")
  @GetMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void findFont(@PathVariable Long id) {
    FontBO obj = fontService.findById(id);
    FontVO fontVO = new FontVO(obj.getId(), obj.getTitle(), obj.getThumbUrl(), obj.getCreateTime(),
        obj.getUpdateTime());
    outputData(fontVO);
  }
}
