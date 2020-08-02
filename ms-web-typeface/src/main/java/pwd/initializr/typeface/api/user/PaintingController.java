package pwd.initializr.typeface.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.typeface.api.user.vo.PaintingListInput;
import pwd.initializr.typeface.api.user.vo.PaintingListOutput;
import pwd.initializr.typeface.api.user.vo.PaintingVO;
import pwd.initializr.typeface.business.PaintingService;
import pwd.initializr.typeface.business.bo.PaintingBO;

/**
 * pwd.initializr.typeface.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 21:34
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "我的图片",
    value = "我的图片Api",
    description = "我的图片API"
)
@RestController(value = "paintingApi")
@RequestMapping(value = "/api/painting")
public class PaintingController extends UserController implements PaintingApi {

  @Autowired
  private PaintingService paintingService;

  @ApiOperation(value = "创建我的图片")
  @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void createPainting(@RequestBody PaintingVO input) {
    PaintingBO paintingBO = new PaintingBO();
    BeanUtils.copyProperties(input, paintingBO);
    paintingBO.setUserId(getUid());
    PaintingBO save = paintingService.create(paintingBO);
    if (save == null) {
      outputData();
    } else {
      BeanUtils.copyProperties(save, input);
      outputData(input);
    }

  }

  @ApiOperation(value = "删除我的图片")
  @DeleteMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void deleteByIds(@RequestBody List<Long> ids) {
    Integer integer = paintingService.deleteByIds(ids);
    outputData(integer.toString());
  }

  @ApiOperation(value = "列表我的图片")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void findByCondition(PaintingListInput input) {
    PaintingBO paintingBO = new PaintingBO();
    paintingBO.setUserId(getUid());
    paintingBO.setContent(input.getContent());
    paintingBO.setFontId(input.getFontId());

    PageableQueryResult<PaintingBO> byCondition = paintingService
        .findByCondition(paintingBO, input.getIndex().longValue(), input.getSize().longValue());

    PaintingListOutput<PaintingVO> output = new PaintingListOutput<PaintingVO>();
    BeanUtils.copyProperties(input, output);
    output.setTotal(byCondition.getTotal());
    output.setPages(byCondition.getPages());
    output.setTotal(byCondition.getTotal());

    List<PaintingVO> collect = byCondition.getElements().stream()
        .map(
            obj -> new PaintingVO(obj.getId(), obj.getFontId(), obj.getFontSize(), obj.getContent(),
                obj.getBackground(), obj.getForeground(), obj.getWidth(), obj.getHeight(),
                obj.getImageUrl(), obj.getCreateTime()))
        .collect(Collectors.toList());

    output.setElements(collect);
    outputData(output);
  }

  @ApiOperation(value = "我的图片详情")
  @GetMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void findById(@PathVariable Long id) {
    PaintingBO obj = paintingService.findById(id);
    PaintingVO paintingVO = new PaintingVO(obj.getId(), obj.getFontId(), obj.getFontSize(),
        obj.getContent(),
        obj.getBackground(), obj.getForeground(), obj.getWidth(), obj.getHeight(),
        obj.getImageUrl(), obj.getCreateTime());
    outputData(paintingVO);
  }
}
