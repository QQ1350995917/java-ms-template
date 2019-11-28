package pwd.initializr.organization.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.api.admin.vo.OrgCreateInput;
import pwd.initializr.organization.api.admin.vo.OrgCreateOutput;
import pwd.initializr.organization.api.admin.vo.OrgListInput;
import pwd.initializr.organization.business.admin.OrganizationService;
import pwd.initializr.organization.business.user.bo.Organization;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 18:02
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "组织管理",
    value = "orgAdminApi",
    description = "组织管理API"
)
@RestController(value = "orgAdminApi")
@RequestMapping(value = "/api/admin/org")
public class OrgController extends AdminController implements OrgApi {

  @Autowired
  private OrganizationService organizationService;

  @ApiOperation(value = "组织列表")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listOrg(OrgListInput input) {
    ObjectList<Organization> organizationObjectList = organizationService
        .listByStatus(input.getStatus());
    outputData(organizationObjectList);
  }

  @ApiOperation(value = "顶级组织信息")
  @GetMapping(value = {"/{orgId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void queryRootOrg(@PathVariable Long orgId) {
    Organization rootOrg = organizationService.getRoot();
    if(rootOrg == null) {
      outputData(404);
    } else {
      outputData(rootOrg);
    }
  }

  @ApiOperation(value = "创建顶级组织")
  @PutMapping(value = {"/root"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void createRootOrg(@RequestBody OrgCreateInput input) {
    Organization rootOrg = organizationService.getRoot();
    if (rootOrg != null) {
      outputData(400); // TODO
    } else {
      Organization organization = new Organization();
      BeanUtils.copyProperties(input, organization);
      Organization createResult = organizationService.createRoot(organization);
      OrgCreateOutput orgCreateOutput = new OrgCreateOutput();
      BeanUtils.copyProperties(createResult, orgCreateOutput);
      outputData(orgCreateOutput);
    }
  }
}
