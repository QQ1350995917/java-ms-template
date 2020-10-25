package pwd.initializr.monitor.api.robot;

import io.swagger.annotations.Api;
import java.util.LinkedHashSet;
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
import pwd.initializr.monitor.api.robot.vo.HostEthernetInput;
import pwd.initializr.monitor.api.robot.vo.HostEthernetOutput;
import pwd.initializr.monitor.business.HostEthernetService;
import pwd.initializr.monitor.business.bo.HostEthernetBO;

/**
* project-generator-test@ms-web-initializr
*
* <h1>HostEthernet控制层接口实现</h1>
*
* date 2020-10-23 11:58
*
* @author Automatic[dingpengwei@foxmail.com]
* @version 0.0.1-SNAPSHOT
* @since 0.0.1-SNAPSHOT
*/
@Api(
  tags = "HostEthernet信息结构",
  value = "HostEthernetManageApi",
  description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "HostEthernet")
@RequestMapping(value = "/api/robot/ethernet")
@Slf4j
public class HostEthernetController extends pwd.initializr.common.web.api.admin.AdminController implements HostEthernetApi {

  @Autowired
  private HostEthernetService service;

  @Override
  public void list(String scopes, String sorts, String page) {
    PageInput pageInput = PageInput.parse(page);
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageableQueryResult<HostEthernetBO> pageableQueryResult = service
      .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
    PageOutput<HostEthernetOutput> result = new PageOutput<>();
    pageableQueryResult.getElements().forEach(bo -> {
      HostEthernetOutput output = new HostEthernetOutput();
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
    HostEthernetBO bo = service.queryById(id);
    HostEthernetOutput output = new HostEthernetOutput();
    BeanUtils.copyProperties(bo,output);
    outputData(output);
  }

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") HostEthernetInput input) {
    HostEthernetBO bo = new HostEthernetBO();
    BeanUtils.copyProperties(input,bo);
    service.insertOrReplace(bo);
    outputData(200,bo.getId());
  }

}
