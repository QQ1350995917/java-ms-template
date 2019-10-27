package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.OrgCreateInput;
import pwd.initializr.account.api.admin.vo.OrgCreateOutput;
import pwd.initializr.account.api.admin.vo.OrgListInput;
import pwd.initializr.account.api.admin.vo.OrgListItem;
import pwd.initializr.account.api.admin.vo.OrgListOutput;
import pwd.initializr.account.business.admin.OrganizationService;
import pwd.initializr.account.business.admin.bo.Organization;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.business.bo.ObjectList;

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

  @Autowired
  private OrganizationService organizationService;

  @ApiOperation(value = "组织列表")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listOrg(OrgListInput input) {
    ObjectList<Organization> organizationObjectList = organizationService
        .listByStatus(input.getStatus());
    OrgListOutput orgListOutput = new OrgListOutput();
    List<OrgListItem> orgListItems = new LinkedList<>();
    for (Organization organization : organizationObjectList.getElements()) {
      OrgListItem orgListItem = new OrgListItem();
      BeanUtils.copyProperties(organization, orgListItem);
      orgListItems.add(orgListItem);
    }
    orgListOutput.setOrg(orgListItems);
    orgListOutput.setHasNext(false);
    outputData(orgListOutput);
  }

  @ApiOperation(value = "顶级组织信息")
  @GetMapping(value = {"/top"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void queryTopOrg() {

  }

  @ApiOperation(value = "创建顶级组织")
  @PostMapping(value = {"/top"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void createTopOrg(OrgCreateInput input) {
    Organization organization = new Organization();
    BeanUtils.copyProperties(input, organization);
    Organization createResult = organizationService.create(organization);
    OrgCreateOutput orgCreateOutput = new OrgCreateOutput();
    BeanUtils.copyProperties(createResult, orgCreateOutput);
    outputData(orgCreateOutput);
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