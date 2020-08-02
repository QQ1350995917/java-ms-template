package pwd.initializr.organization.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.organization.business.user.OrganizationMemberInfoService;
import pwd.initializr.organization.business.user.OrganizationMemberService;
import pwd.initializr.organization.business.user.bo.OrganizationMember;
import pwd.initializr.organization.business.user.bo.OrganizationMemberInfo;

/**
 * pwd.initializr.organization.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-07 10:58
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "组织成员管理",
    value = "orgMemApi",
    description = "[获取成员信息]"
)
@RestController(value = "orgMemApi")
@RequestMapping(value = "/api/org/mem")
public class OrgMemController extends UserController implements OrgMemApi {

  @Autowired
  private OrganizationMemberService organizationMemberService;
  @Autowired
  private OrganizationMemberInfoService organizationMemberInfoService;


  @ApiOperation(value = "获取成员信息")
  @GetMapping(value = {"/{orgId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listOrgMem(@PathVariable("orgId") Long orgId) {
    PageableQueryResult<OrganizationMember> organizationMemberPageableQueryResult = organizationMemberService
        .listByOrgId(orgId, null);// TODO 支持状态
    List<Long> memIds = new LinkedList<>();
    for (OrganizationMember element : organizationMemberPageableQueryResult.getElements()) {
      memIds.add(element.getMemId());
    }
    List<OrganizationMemberInfo> organizationMemberInfos = organizationMemberInfoService
        .fetchMemberInfo(memIds.toArray(new Long[]{})); // TODO 动态ID
    outputData(organizationMemberInfos);
  }
}
