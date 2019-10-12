package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 18:02
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "后台组织管理",
    value = "orgAdminApi",
    description = "后台组织管理API"
)
@RestController(value = "orgAdminApi")
@RequestMapping(value = "/api/admin/org")
public class OrgController extends AdminController implements OrgApi {

  @ApiOperation(value = "组织列表")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listOrg() {

  }

  @ApiOperation(value = "顶级组织信息")
  @GetMapping(value = {"/top"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void queryTopOrg() {

  }

  @ApiOperation(value = "创建顶级组织")
  @PostMapping(value = {"/top"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void createTopOrg() {

  }

  @ApiOperation(value = "组织审核中")
  @PostMapping(value = {"/review/exec"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void reviewExecution() {

  }

  @ApiOperation(value = "拒绝新创建的组织")
  @PostMapping(value = {"/review/refuse"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void reviewRefuse() {

  }

  @ApiOperation(value = "批准新创建的组织")
  @PostMapping(value = {"/review/approve"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void reviewApprove() {

  }

  @ApiOperation(value = "从新审核组织信息")
  @PostMapping(value = {"/review/recheck"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void reviewRecheck() {

  }
}
