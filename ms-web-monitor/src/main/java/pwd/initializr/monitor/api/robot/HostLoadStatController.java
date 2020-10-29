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
import pwd.initializr.monitor.api.robot.vo.HostLoadStatInput;
import pwd.initializr.monitor.api.robot.vo.HostLoadStatOutput;
import pwd.initializr.monitor.business.HostLoadStatService;
import pwd.initializr.monitor.business.bo.HostLoadStatBO;

/**
* project-generator-test@ms-web-initializr
*
* <h1>HostLoadStat控制层接口实现</h1>
*
* date 2020-10-29 11:44
*
* @author Automatic[dingpengwei@foxmail.com]
* @version 0.0.1-SNAPSHOT
* @since 0.0.1-SNAPSHOT
*/
@Api(
  tags = "HostLoadStat信息结构",
  value = "HostLoadStatManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "HostLoadStat")
@RequestMapping(value = "/api/robot/host/load/stat")
@Slf4j
public class HostLoadStatController extends pwd.initializr.common.web.api.admin.AdminController implements HostLoadStatApi {

  @Autowired
  private HostLoadStatService service;

  @Override
  public void list(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<HostLoadStatBO> pageableQueryResult = service
      .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<HostLoadStatOutput> result = new PageOutput<>();
    pageableQueryResult.getElements().forEach(bo -> {
      HostLoadStatOutput output = new HostLoadStatOutput();
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
    HostLoadStatBO bo = service.queryById(id);
    HostLoadStatOutput output = new HostLoadStatOutput();
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
  public void create(@Valid @NotNull(message = "参数不能为空") HostLoadStatInput input) {
    HostLoadStatBO bo = new HostLoadStatBO();
    BeanUtils.copyProperties(input,bo);
    service.insert(bo);
    outputData(200);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") List<HostLoadStatInput> input) {
    LinkedList<HostLoadStatBO> bos = new LinkedList<>();
    for (HostLoadStatInput item : input) {
      HostLoadStatBO bo = new HostLoadStatBO();
      BeanUtils.copyProperties(item,bo);
      bos.add(bo);
    }
    service.insert(bos);
    outputData(200);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") HostLoadStatInput input) {
    HostLoadStatBO bo = new HostLoadStatBO();
    BeanUtils.copyProperties(input,bo);
    service.insertOrReplace(bo);
    outputData(200);
  }

  @Override
  public void createOrReplace(@Valid @NotNull(message = "参数不能为空") List<HostLoadStatInput> input) {
    LinkedList<HostLoadStatBO> bos = new LinkedList<>();
    for (HostLoadStatInput item : input) {
      HostLoadStatBO bo = new HostLoadStatBO();
      BeanUtils.copyProperties(item,bo);
      bos.add(bo);
    }
    service.insertOrReplace(bos);
    outputData(200);
  }

  @Override
  public void update(@Valid @NotNull(message = "参数不能为空") Long id,
  @Valid @NotNull(message = "参数不能为空") HostLoadStatInput input) {
    HostLoadStatBO bo = new HostLoadStatBO();
    BeanUtils.copyProperties(input,bo);
    Integer result = service.updateById(bo);
    outputData(200,result);
  }

}
