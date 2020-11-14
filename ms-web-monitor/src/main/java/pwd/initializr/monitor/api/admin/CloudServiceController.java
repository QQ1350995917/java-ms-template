package pwd.initializr.monitor.api.admin;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Applications;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.monitor.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-11-13 21:51
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
public class CloudServiceController extends
    pwd.initializr.common.web.api.admin.AdminController implements CloudServiceApi {

  @Autowired
  private DiscoveryClient discoveryClient;

  @Override
  public void listService(String scopes, String sorts, String page) {
    Applications applications = discoveryClient.getApplications();
    outputData(applications);
  }
}
