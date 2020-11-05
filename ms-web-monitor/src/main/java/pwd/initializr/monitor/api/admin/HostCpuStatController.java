package pwd.initializr.monitor.api.admin;

import io.swagger.annotations.Api;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
import pwd.initializr.monitor.api.admin.vo.HostCpuStatOutput;
import pwd.initializr.monitor.api.admin.vo.HostCpuStatStatisticsOutput;
import pwd.initializr.monitor.business.HostCpuService;
import pwd.initializr.monitor.business.HostCpuStatService;
import pwd.initializr.monitor.business.bo.HostCpuBO;
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
    tags = "AdminHostCpuStat信息结构",
    value = "AdminHostCpuStatManageApi",
    description = "[列表查询，详情查询，启/禁用，删除，新增，修改]"
)
@RestController(value = "AdminHostCpuStat")
@RequestMapping(value = "/api/admin/host/cpu/stat")
@Slf4j
public class HostCpuStatController extends
    pwd.initializr.common.web.api.admin.AdminController implements
    HostCpuStatApi {

  @Autowired
  private HostCpuService cpuService;

  @Autowired
  private HostCpuStatService cpuStatService;

  @Override
  public void delete(@Valid @NotNull(message = "参数不能为空") Long id) {
    Integer result = cpuStatService.deleteById(id);
    outputData(200, result);
  }

  @Override
  public void delete(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
    Integer result = cpuStatService.deleteById(ids);
    outputData(200, result);
  }

  @Override
  public void detail(@Valid @NotNull(message = "参数不能为空") Long id) {
    HostCpuStatBO bo = cpuStatService.queryById(id);
    HostCpuStatOutput output = new HostCpuStatOutput();
    BeanUtils.copyProperties(bo, output);
    outputData(output);
  }

  @Override
  public void list(String scopes, String sorts, String page) {
    LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
    LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
    PageInput pageInput = PageInput.parse(page);
    PageOutput<HostCpuStatOutput> result = this.list(scopeBOS, sortBOS, pageInput);
    outputData(result);
  }

  @Override
  public void statistics(@Valid @NotNull(message = "参数不能为空") String groupName,
      @Valid @NotNull(message = "参数不能为空") String nodeName,
      @Valid @NotNull(message = "参数不能为空") Integer times) {
    HostCpuBO hostCpuBO = cpuService.queryById(groupName, nodeName,"0");
    if (hostCpuBO == null) {
      outputException(401,"找不到" + groupName + "/" + nodeName + "的CPU信息");
      return;
    }
    if (StringUtils.isBlank(hostCpuBO.getCpuCores())) {
      outputException(401,groupName + "/" + nodeName + "的CPU核心数量不明，请检查采集数据格式");
      return;
    }
    int cpuCores = Integer.parseInt(hostCpuBO.getCpuCores().trim());
    int size = cpuCores * times;

    LinkedHashSet<ScopeBO> scopeBOS = new LinkedHashSet<>();
    ScopeBO groupNameScopeBO = new ScopeBO();
    groupNameScopeBO.setFieldName("group_name");
    groupNameScopeBO.setFieldValue(groupName);
    groupNameScopeBO.setHit("E");
    scopeBOS.add(groupNameScopeBO);

    ScopeBO nodeNameScopeBO = new ScopeBO();
    nodeNameScopeBO.setFieldName("node_name");
    nodeNameScopeBO.setFieldValue(nodeName);
    nodeNameScopeBO.setHit("E");
    scopeBOS.add(nodeNameScopeBO);

    LinkedHashSet<SortBO> sortBOS = new LinkedHashSet<>();
    SortBO sortBO = new SortBO();
    sortBO.setFieldName("create_time");
    sortBO.setSort("desc");
    sortBOS.add(sortBO);

    PageInput pageInput = new PageInput();
    pageInput.setIndex(0L);
    pageInput.setSize(Integer.toUnsignedLong(size));

    PageOutput<HostCpuStatOutput> list = this.list(scopeBOS, sortBOS, pageInput);
    if (list == null || list.getElements() == null) {
      outputException(401,"没有查到数据，请稍后重试");
      return;
    }
    LinkedHashMap<String,List<HostCpuStatOutput>> outputMap = new LinkedHashMap<>();
    List<HostCpuStatOutput> elements = list.getElements();
    for (HostCpuStatOutput element : elements) {
      List<HostCpuStatOutput> listOfPreCpuCore = outputMap.get(element.getCpuCoreName());
      if (listOfPreCpuCore == null) {
        listOfPreCpuCore = new LinkedList<>();
        outputMap.put(element.getCpuCoreName(),listOfPreCpuCore);
      }
      listOfPreCpuCore.add(element);
    }

    LinkedList<HostCpuStatStatisticsOutput> output = new LinkedList<>();
    for (String key : outputMap.keySet()) {
      HostCpuStatStatisticsOutput hostCpuStatStatisticsOutput = new HostCpuStatStatisticsOutput();
      hostCpuStatStatisticsOutput.setCpuCoreName(key);
      hostCpuStatStatisticsOutput.setCpuStats(outputMap.get(key));
      output.add(hostCpuStatStatisticsOutput);
    }
    outputData(output);
  }

  private PageOutput<HostCpuStatOutput> list(LinkedHashSet<ScopeBO> scopes,LinkedHashSet<SortBO> sorts,PageInput page){
    PageableQueryResult<HostCpuStatBO> pageableQueryResult = cpuStatService
        .queryAllByCondition(scopes, sorts, page.getIndex(), page.getSize());
    PageOutput<HostCpuStatOutput> result = new PageOutput<>();
    pageableQueryResult.getElements().forEach(bo -> {
      HostCpuStatOutput output = new HostCpuStatOutput();
      BeanUtils.copyProperties(bo, output);
      result.getElements().add(output);
    });
    result.setTotal(pageableQueryResult.getTotal());
    result.setIndex(pageableQueryResult.getIndex());
    result.setSize(pageableQueryResult.getSize());
    return result;
  }
}
