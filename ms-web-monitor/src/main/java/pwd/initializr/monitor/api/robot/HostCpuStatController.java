package pwd.initializr.monitor.api.robot;

import io.swagger.annotations.Api;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.monitor.api.robot.vo.HostCpuStatInput;
import pwd.initializr.monitor.api.robot.vo.HostCpuStatOutput;
import pwd.initializr.monitor.business.HostCpuStatService;
import pwd.initializr.monitor.business.bo.HostCpuStatBO;

/**
* project-generator-test@ms-web-initializr
*
* <h1>HostCpuStat控制层接口实现</h1>
*
* date 2020-10-29 11:44
*
* @author Automatic[dingpengwei@foxmail.com]
* @version 0.0.1-SNAPSHOT
* @since 0.0.1-SNAPSHOT
*/
@Api(
  tags = "RobotHostCpuStat信息结构",
  value = "RobotHostCpuStatManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "RobotHostCpuStat")
@RequestMapping(value = "/api/robot/host/cpu/stat")
@Slf4j
public class HostCpuStatController extends pwd.initializr.common.web.api.admin.AdminController implements HostCpuStatApi {

  @Autowired
  private HostCpuStatService service;

  @Override
  public void list(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<HostCpuStatBO> pageableQueryResult = service
      .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<HostCpuStatOutput> result = new PageOutput<>();
    pageableQueryResult.getElements().forEach(bo -> {
      HostCpuStatOutput output = new HostCpuStatOutput();
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
    HostCpuStatBO bo = service.queryById(id);
    HostCpuStatOutput output = new HostCpuStatOutput();
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
  public void create(@Valid @NotNull(message = "参数不能为空") HostCpuStatInput input) {
    HostCpuStatBO bo = new HostCpuStatBO();
    BeanUtils.copyProperties(input,bo);
    service.insert(bo);
    outputData(200);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") List<HostCpuStatInput> input) {
    LinkedList<HostCpuStatBO> bos = new LinkedList<>();
    for (HostCpuStatInput item : input) {
      HostCpuStatBO bo = new HostCpuStatBO();
      BeanUtils.copyProperties(item,bo);
      bos.add(bo);
    }
    service.insert(bos);
    outputData(200);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") HostCpuStatInput input) {
    HostCpuStatBO bo = new HostCpuStatBO();
    BeanUtils.copyProperties(input,bo);
    service.insertOrReplace(bo);
    outputData(200);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") List<HostCpuStatInput> input) {
    LinkedList<HostCpuStatBO> bos = new LinkedList<>();
    for (HostCpuStatInput item : input) {
      HostCpuStatBO bo = new HostCpuStatBO();
      BeanUtils.copyProperties(item,bo);
      bos.add(bo);
    }
    service.insertOrReplace(bos);
    outputData(200);
  }

  @Override
  public void update(@Valid @NotNull(message = "参数不能为空") Long id,
  @Valid @NotNull(message = "参数不能为空") HostCpuStatInput input) {
    HostCpuStatBO bo = new HostCpuStatBO();
    BeanUtils.copyProperties(input,bo);
    Integer result = service.updateById(bo);
    outputData(200,result);
  }

}
