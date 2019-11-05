package pwd.initializr.organization.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.api.admin.vo.OrgReviewInput;
import pwd.initializr.organization.api.admin.vo.OrgReviewOutput;
import pwd.initializr.organization.business.admin.OrganizationProgressService;
import pwd.initializr.organization.business.admin.bo.OrganizationProgress;

/**
 * pwd.initializr.organization.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 13:59
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "组织审核管理",
    value = "orgReviewAdminApi",
    description = "组织审核管理API"
)
@RestController(value = "orgReviewAdminApi")
@RequestMapping(value = "/api/admin/org/review")
public class OrgReviewController extends AdminController implements OrgReviewApi {

  @Autowired
  private OrganizationProgressService organizationProgressService;

  @ApiOperation(value = "审核列表")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listReview(Long orgId) {
    ObjectList<OrganizationProgress> organizationProgressObjectList = organizationProgressService
        .listReviewByOrgId(orgId, null);
    outputData(organizationProgressObjectList);
  }

  @ApiOperation(value = "可选状态列表")
  @GetMapping(value = {"/option"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listReviewOption() {
    outputData(organizationProgressService.listReviewOption());
  }

  @ApiOperation(value = "审核组织")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void review(@RequestBody OrgReviewInput input) {
    OrganizationProgress organizationProgress = new OrganizationProgress();
    BeanUtils.copyProperties(input, organizationProgress);
    organizationProgress.setEditorId(1L); // TODO ID
    organizationProgressService.createReview(organizationProgress);
    OrgReviewOutput orgReviewOutput = new OrgReviewOutput();
    BeanUtils.copyProperties(organizationProgress, orgReviewOutput);
    outputData(orgReviewOutput);
  }
}
