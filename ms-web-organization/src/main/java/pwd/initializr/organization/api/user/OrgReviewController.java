package pwd.initializr.organization.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.api.user.vo.ReviewPendingInput;
import pwd.initializr.organization.api.user.vo.ReviewPendingOutput;
import pwd.initializr.organization.business.user.OrganizationProgressService;
import pwd.initializr.organization.business.user.bo.OrganizationProgress;

/**
 * pwd.initializr.organization.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 14:36
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
@RequestMapping(value = "/api/org/review")
public class OrgReviewController extends UserController implements OrgReviewApi {

  @Autowired
  private OrganizationProgressService organizationProgressService;

  @ApiOperation(value = "查询组织审核信息")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listReviewPending(Long orgId) {
    // TODO 检查orgId和当前memId的匹配性
    ObjectList<OrganizationProgress> organizationProgressObjectList = organizationProgressService
        .listReviewPending(1L, null);
    List<ReviewPendingOutput> result = new LinkedList<>();
    for (OrganizationProgress organizationProgress : organizationProgressObjectList.getElements()) {
      ReviewPendingOutput reviewPendingOutput = new ReviewPendingOutput();
      BeanUtils.copyProperties(organizationProgress, reviewPendingOutput);
      result.add(reviewPendingOutput);
    }
    outputData(result);
  }


  @ApiOperation(value = "提交组织到待审核")
  @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void reviewPending(@RequestBody ReviewPendingInput input) {
    OrganizationProgress organizationProgress = new OrganizationProgress();
    BeanUtils.copyProperties(input, organizationProgress);
    organizationProgress.setEditorId(1L); // TODO ID
    organizationProgressService.reviewPending(organizationProgress);
    outputData();
  }
}
