package pwd.initializr.typeface.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.typeface.api.user.vo.FontListInput;
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
@RequestMapping(value = "/api/user/font")
public class FontController extends UserController implements FontApi {

  @Autowired
  private FontService fontService;

  @ApiOperation(value = "字体列表查询")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listFont(FontListInput input) {
    FontBO fontBO = new FontBO();
    fontBO.setTitle(input.getTitle());
    ObjectList<FontBO> byCondition = fontService
        .findByCondition(fontBO, input.getIndex(), input.getSize());
    FontListOutput<FontVO> output = new FontListOutput<FontVO>();
    BeanUtils.copyProperties(input, output);
    output.setTotal(byCondition.getTotal());
    output.setPages(byCondition.getPages());
    output.setTotal(byCondition.getTotal());
    List<FontVO> collect = byCondition.getElements().stream().map(
        obj -> new FontVO(obj.getId(), obj.getTitle(), obj.getThumbUrl(), obj.getCreateTime(),
            obj.getUpdateTime()))
        .collect(Collectors.toList());
    output.setData(collect);
    outputData(output);
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
