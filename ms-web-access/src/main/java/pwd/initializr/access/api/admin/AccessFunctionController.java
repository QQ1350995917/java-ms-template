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
import pwd.initializr.access.api.admin.vo.AccessFunctionInput;
import pwd.initializr.access.api.admin.vo.AccessFunctionOutput;
import pwd.initializr.access.business.admin.AccessFunctionService;
import pwd.initializr.access.business.admin.bo.AccessFunctionBO;
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
 * <h1>AccessFunction控制层接口实现</h1>
 *
 * date 2021-08-08 15:20
 *
 * @author Automatic[dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Api(
  tags = "AccessFunction信息结构",
  value = "AccessFunctionManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "AccessFunction")
@RequestMapping(value = "/api/access/function")
@Slf4j
public class AccessFunctionController extends pwd.initializr.common.web.api.admin.AdminController implements AccessFunctionApi {

  @Autowired
  private AccessFunctionService service;

  @Override
  public void list(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<AccessFunctionBO> pageableQueryResult = service.queryAllByCondition(scopeBOS,
      sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<AccessFunctionOutput> result = new PageOutput<>();
    pageableQueryResult.getElements().forEach(bo -> {
      AccessFunctionOutput output = new AccessFunctionOutput();
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
    AccessFunctionBO bo = service.queryById(id);
    AccessFunctionOutput output = new AccessFunctionOutput();
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
  public void create(@Valid @NotNull(message = "参数不能为空") AccessFunctionInput input) {
    AccessFunctionBO bo = new AccessFunctionBO();
    BeanUtils.copyProperties(input,bo);
    Long id = service.insert(bo);
    outputData(200,id);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") List<AccessFunctionInput> input) {
  List<AccessFunctionBO> bos = input.stream().map(this::convertAccessFunctionInput2AccessFunctionBO)
    .collect(Collectors.toList());
    service.insert(bos);
    outputData(200);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") AccessFunctionInput input) {
    AccessFunctionBO bo = new AccessFunctionBO();
    BeanUtils.copyProperties(input,bo);
    Long id = service.insertOrReplace(bo);
    outputData(200,id);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") List<AccessFunctionInput> input) {
  List<AccessFunctionBO> bos = input.stream().map(this::convertAccessFunctionInput2AccessFunctionBO)
    .collect(Collectors.toList());
    service.insertOrReplace(bos);
    outputData(200);
  }

  @Override
  public void update(@Valid @NotNull(message = "参数不能为空") Long id,
    @Valid @NotNull(message = "参数不能为空") AccessFunctionInput input) {
    AccessFunctionBO bo = new AccessFunctionBO();
    BeanUtils.copyProperties(input,bo);
    bo.setFid(id);
    Integer result = service.updateById(bo);
    outputData(200,result);
  }

  private AccessFunctionBO convertAccessFunctionInput2AccessFunctionBO(AccessFunctionInput input){
    AccessFunctionBO bo = new AccessFunctionBO();
    BeanUtils.copyProperties(input,bo);
    return bo;
  }

}