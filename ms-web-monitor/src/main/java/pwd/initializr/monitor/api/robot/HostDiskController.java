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
import pwd.initializr.monitor.api.robot.vo.HostDiskInput;
import pwd.initializr.monitor.api.robot.vo.HostDiskOutput;
import pwd.initializr.monitor.business.HostDiskService;
import pwd.initializr.monitor.business.bo.HostDiskBO;

/**
* project-generator-test@ms-web-initializr
*
* <h1>HostDisk控制层接口实现</h1>
*
* date 2020-10-23 11:58
*
* @author Automatic[dingpengwei@foxmail.com]
* @version 0.0.1-SNAPSHOT
* @since 0.0.1-SNAPSHOT
*/
@Api(
  tags = "HostDisk信息结构",
  value = "HostDiskManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "HostDisk")
@RequestMapping(value = "/api/robot/disk")
@Slf4j
public class HostDiskController extends pwd.initializr.common.web.api.admin.AdminController implements HostDiskApi {

  @Autowired
  private HostDiskService service;

  @Override
  public void list(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<HostDiskBO> pageableQueryResult = service
      .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<HostDiskOutput> result = new PageOutput<>();
    pageableQueryResult.getElements().forEach(bo -> {
      HostDiskOutput output = new HostDiskOutput();
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
    HostDiskBO bo = service.queryById(id);
    HostDiskOutput output = new HostDiskOutput();
    BeanUtils.copyProperties(bo,output);
    outputData(output);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") List<HostDiskInput> input) {
    LinkedList<HostDiskBO> hostDiskBOS = new LinkedList<>();
    for (HostDiskInput hostDiskInput : input) {
      HostDiskBO bo = new HostDiskBO();
      BeanUtils.copyProperties(hostDiskInput,bo);
      hostDiskBOS.add(bo);
    }
    service.insertOrReplace(hostDiskBOS);
    outputData(200);
  }
}
