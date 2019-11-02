package pwd.initializr.organization.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.organization.business.admin.OrganizationService;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 18:11
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "用户组织管理",
    value = "orgApi",
    description = "用户组织API"
)
@RestController(value = "orgApi")
@RequestMapping(value = "/api/org")
public class OrgController extends UserController implements OrgApi {

  @Autowired
  private OrganizationService organizationService;

  @ApiOperation(value = "组织列表")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listOrg() {

  }

  @ApiOperation(value = "创建组织")
  @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void create() {

  }


  @ApiOperation(value = "提交组织到待审核")
  @PostMapping(value = {"/pending"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void reviewPending() {

  }
}
