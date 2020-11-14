package pwd.initializr.monitor.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.monitor.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-11-13 21:53
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "CloudService信息结构",
    value = "CloudServiceApi",
    description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "CloudService")
@RequestMapping(value = "/api/admin/cloud/service")
public interface CloudServiceApi {

  /**
   * <h2>根据分页信息以及分页条件以列表形式查询</h2>
   * date 2020-10-29 11:44
   *
   * @param scopes 查询参数
   * @param sorts 排序信息
   * @param page 分页信息
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "根据分页信息以及分页条件以列表形式查询")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void listService(
      @ApiParam(name = "scopes", value = "[{\"hit\":\"指定查询方式[E:精确,AL:前后模糊,LL:左模糊,RL:右模糊,S:范围]\",\"fieldName\":\"指定查询字段\",\"fieldValue\":\"指定查询目标\",\"start\":\"范围查询起始值，区间包含\",\"end\":\"范围查询结束值，区间包含\"}]") @RequestParam(value = "scopes", required = false) String scopes,
      @ApiParam(name = "sorts", value = "[{\"fieldName\":\"指定排序字段\",\"sort\":\"[desc|asc]\"}]") @RequestParam(value = "sorts", required = false) String sorts,
      @ApiParam(name = "page", value = "{\"index\":0,\"size\":12}") @RequestParam(value = "page", required = false) String page);

}
