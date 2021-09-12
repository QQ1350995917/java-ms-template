package pwd.initializr.access.api.admin;

import io.swagger.annotations.Api;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.access.api.admin.vo.UserRoleInput;
import pwd.initializr.access.api.admin.vo.UserRoleOutput;
import pwd.initializr.access.business.admin.UserRoleService;
import pwd.initializr.access.business.admin.bo.UserRoleBO;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd-initializr-access@ms-web-initializr
 *
 * <h1>UserRole控制层接口实现</h1>
 *
 * date 2021-08-08 15:20
 *
 * @author Automatic[dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Api(
  tags = "UserRole信息结构",
  value = "UserRoleManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "UserRole")
@RequestMapping(value = "/api/user/role")
@Slf4j
public class UserRoleController extends pwd.initializr.common.web.api.admin.AdminController implements UserRoleApi {

  @Autowired
  private UserRoleService service;

  @Override
  public void list(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<UserRoleBO> pageableQueryResult = service.queryAllByCondition(scopeBOS,
      sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<UserRoleOutput> result = new PageOutput<>();
    pageableQueryResult.getElements().forEach(bo -> {
      UserRoleOutput output = new UserRoleOutput();
      BeanUtils.copyProperties(bo, output);
      result.getElements().add(output);
    });
    result.setTotal(pageableQueryResult.getTotal());
    result.setIndex(pageableQueryResult.getIndex());
    result.setSize(pageableQueryResult.getSize());
    outputData(result);
  }

  @Override
  public void detail(@Valid @NotNull(message = "参数不能为空") Long id) {
    UserRoleBO bo = service.queryById(id);
    UserRoleOutput output = new UserRoleOutput();
    BeanUtils.copyProperties(bo,output);
    outputData(output);
  }

  @Override
  public void enable(@Valid @NotNull(message = "参数不能为空") Long id) {
    Integer result = service.ableById(id, EntityAble.ENABLE);
    outputData(200,result);
  }

  @Override
  public void enable(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
    Integer result = service.ableById(ids, EntityAble.ENABLE);
    outputData(200,result);
  }

  @Override
  public void disable(@Valid @NotNull(message = "参数不能为空") Long id) {
    Integer result = service.ableById(id, EntityAble.DISABLE);
    outputData(200,result);
  }

  @Override
  public void disable(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
    Integer result = service.ableById(ids, EntityAble.DISABLE);
    outputData(200,result);
  }

  @Override
  public void delete(@Valid @NotNull(message = "参数不能为空") Long id) {
    Integer result = service.deleteById(id);
    outputData(200,result);
  }

  @Override
  public void delete(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
    Integer result = service.deleteById(ids);
    outputData(200,result);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") UserRoleInput input) {
    UserRoleBO bo = new UserRoleBO();
    BeanUtils.copyProperties(input,bo);
    Long id = service.insert(bo);
    outputData(200,id);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") List<UserRoleInput> input) {
  List<UserRoleBO> bos = input.stream().map(this::convertUserRoleInput2UserRoleBO)
    .collect(Collectors.toList());
    service.insert(bos);
    outputData(200);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") UserRoleInput input) {
    UserRoleBO bo = new UserRoleBO();
    BeanUtils.copyProperties(input,bo);
    Long id = service.insertOrReplace(bo);
    outputData(200,id);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") List<UserRoleInput> input) {
  List<UserRoleBO> bos = input.stream().map(this::convertUserRoleInput2UserRoleBO)
    .collect(Collectors.toList());
    service.insertOrReplace(bos);
    outputData(200);
  }

  @Override
  public void update(@Valid @NotNull(message = "参数不能为空") Long id,
    @Valid @NotNull(message = "参数不能为空") UserRoleInput input) {
    UserRoleBO bo = new UserRoleBO();
    BeanUtils.copyProperties(input,bo);
    bo.setRid(id);
    Integer result = service.updateById(bo);
    outputData(200,result);
  }

  private UserRoleBO convertUserRoleInput2UserRoleBO(UserRoleInput input){
    UserRoleBO bo = new UserRoleBO();
    BeanUtils.copyProperties(input,bo);
    return bo;
  }

}