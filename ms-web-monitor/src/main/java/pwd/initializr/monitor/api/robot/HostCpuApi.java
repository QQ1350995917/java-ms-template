package pwd.initializr.monitor.api.robot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.monitor.api.robot.vo.HostCpuInput;

/**
 * project-generator-test@ms-web-initializr
 *
 * <h1>HostCpu控制层接口声明</h1>
 *
 * date 2020-10-23 11:58
 *
 * @author Automatic[dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Api(
  tags = "HostCpu信息结构",
  value = "HostCpuManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "HostCpu")
@RequestMapping(value = "/api/robot/cpu")
public interface HostCpuApi {

  /**
   * <h2>根据分页信息以及分页条件以列表形式查询</h2>
   * date 2020-10-23 11:58
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
  void list(
      @ApiParam(name = "scopes", value = "[{\"hit\":\"指定查询方式[E:精确,AL:前后模糊,LL:左模糊,RL:右模糊,S:范围]\",\"fieldName\":\"指定查询字段\",\"fieldValue\":\"指定查询目标\",\"start\":\"范围查询起始值，区间包含\",\"end\":\"范围查询结束值，区间包含\"}]") @RequestParam(value = "scopes", required = false) String scopes,
      @ApiParam(name = "sorts", value = "[{\"fieldName\":\"指定排序字段\",\"sort\":\"[desc|asc]\"}]") @RequestParam(value = "sorts", required = false) String sorts,
      @ApiParam(name = "page", value = "{\"index\":0,\"size\":12}") @RequestParam(value = "page", required = false) String page);

  /**
   * <h2>根据ID查询详情</h2>
   * date 2020-10-23 11:58
   *
   * @param id 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "根据ID查询详情")
  @GetMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void detail(@PathVariable("id") @Valid @NotNull(message = "参数不能为空") Long id);

  /**
   * <h2>新增</h2>
   * date 2020-10-23 11:58
   *
   * @param input 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "新增")
  @PostMapping(value = {""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void create(@RequestBody @Valid @NotNull(message = "参数不能为空") HostCpuInput input);


}
