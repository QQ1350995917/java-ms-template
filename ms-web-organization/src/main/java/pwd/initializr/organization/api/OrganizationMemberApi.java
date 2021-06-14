package pwd.initializr.organization.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.organization.api.vo.OrganizationMemberInput;

/**
 * pwd-initializr-organization@ms-web-initializr
 *
 * <h1>OrganizationMember控制层接口声明</h1>
 *
 * date 2021-02-22 21:33
 *
 * @author Automatic[dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Api(
  tags = "OrganizationMember信息结构",
  value = "OrganizationMemberManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "OrganizationMember")
@RequestMapping(value = "/api/organization/member")
public interface OrganizationMemberApi {

  /**
   * <h2>根据分页信息以及分页条件以列表形式查询</h2>
   * date 2021-02-22 21:33
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
  @ApiParam(name = "scopes", value = "[{\"hit\":\"指定查询方式[EM:精确,ENM:精确非,AL:前后模糊,LL:左模糊,RL:右模糊,SM:范围\",\"fieldName\":\"指定查询字段\",\"fieldValue\":\"指定查询目标\",\"start\":\"范围查询起始值，区间包含\",\"end\":\"范围查询结束值，区间包含\"}]") @RequestParam(value = "scopes", required = false) String scopes,
  @ApiParam(name = "sorts", value = "[{\"fieldName\":\"指定排序字段\",\"sort\":\"[desc|asc]\"}]") @RequestParam(value = "sorts", required = false) String sorts,
  @ApiParam(name = "page", value = "{\"index\":0,\"size\":12}") @RequestParam(value = "page", required = false) String page);

  /**
   * <h2>根据ID查询详情</h2>
   * date 2021-02-22 21:33
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
   * <h2>启用</h2>
   * date 2021-02-22 21:33
   *
   * @param id 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "启用")
  @PutMapping(value = {"/enable/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void enable(@PathVariable("id") @Valid @NotNull(message = "参数不能为空") Long id);

  /**
   * <h2>批量启用</h2>
   * date 2021-02-22 21:33
   *
   * @param ids 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "启用")
  @PutMapping(value = {"/enable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void enable(@RequestBody @Valid @NotNull(message = "参数不能为空") Set<Long> ids);

  /**
   * <h2>禁用</h2>
   * date 2021-02-22 21:33
   *
   * @param id 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "禁用")
  @PutMapping(value = {"/disable/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void disable(@PathVariable("id") @Valid @NotNull(message = "参数不能为空") Long id);

  /**
   * <h2>批量禁用</h2>
   * date 2021-02-22 21:33
   *
   * @param ids 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "禁用")
  @PutMapping(value = {"/disable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void disable(@RequestBody @Valid @NotNull(message = "参数不能为空") Set<Long> ids);

  /**
   * <h2>删除</h2>
   * date 2021-02-22 21:33
   *
   * @param id 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "删除")
  @DeleteMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void delete(@PathVariable("id") @Valid @NotNull(message = "参数不能为空") Long id);

  /**
   * <h2>批量删除</h2>
   * date 2021-02-22 21:33
   *
   * @param ids 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "批量删除")
  @DeleteMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void delete(@RequestBody @Valid @NotNull(message = "参数不能为空") Set<Long> ids);

  /**
   * <h2>新增</h2>
   * date 2021-02-22 21:33
   *
   * @param orgId 组织ID
   * @param memId 成员ID
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "新增")
  @PostMapping(value = {"/{orgId}/{memId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void create(
      @PathVariable("orgId") @Valid @NotNull(message = "参数不能为空") Long orgId,
      @PathVariable("memId") @Valid @NotNull(message = "参数不能为空") Long memId);

  /**
   * <h2>新增（批量）</h2>
   * date 2021-02-22 21:33
   *
   * @param input 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "新增（批量）")
  @PostMapping(value = {"/batch"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void create(@RequestBody @Valid @NotNull(message = "参数不能为空") List<OrganizationMemberInput> input);

  /**
   * <h2>新增或替换</h2>
   * date 2021-02-22 21:33
   *
   * @param input 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "新增或替换")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void createOrReplace(@RequestBody @Valid @NotNull(message = "参数不能为空") OrganizationMemberInput input);

  /**
   * <h2>新增或替换（批量）</h2>
   * date 2021-02-22 21:33
   *
   * @param input 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "新增或替换（批量）")
  @PutMapping(value = {"/batch"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void createOrReplace(@RequestBody @Valid @NotNull(message = "参数不能为空") List<OrganizationMemberInput> input);

  /**
   * <h2>更新</h2>
   * date 2021-02-22 21:33
   *
   * @param input 请求参数
   * @return void
   * @author Automatic[www.dingpengwei@foxmail.com]
   * @since 0.0.1-SNAPSHOT
   */
  @ApiOperation(value = "更新")
  @PatchMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void update(@PathVariable("id") @Valid @NotNull(message = "参数不能为空") Long id,
    @RequestBody @Valid @NotNull(message = "参数不能为空") OrganizationMemberInput input);

}
