package pwd.initializr.monitor.api.admin;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.monitor.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-19 16:46
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "服务器信息",
    value = "服务器信息",
    description = "[OS，CPU，MEMORY，SWAP，WHO，DISK，NETWORK，ETHERNET]"
)
@RestController(value = "server")
@RequestMapping(value = "/api/admin/server")
@Slf4j
public class SystemController extends AdminController implements SystemApi {


  @Override
  public void os() {
    this.outputData();
  }

  @Override
  public void cpu() {
    this.outputData();
  }

  @Override
  public void memory() {
    this.outputData();
  }

  @Override
  public void swap() {

    this.outputData();
  }

  @Override
  public void who() {
    this.outputData();
  }

  @Override
  public void disk() {
    this.outputData();
  }

  @Override
  public void network() {
    this.outputData();
  }

  @Override
  public void ethernet() {
    this.outputData();
  }
}
