package pwd.initializr.organization.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import pwd.initializr.organization.api.user.vo.CreateOrgInput;
import pwd.initializr.organization.api.user.vo.CreateOrgOutput;
import pwd.initializr.organization.api.user.vo.ListMineOrgOutput;
import pwd.initializr.organization.business.user.OrganizationMemberService;
import pwd.initializr.organization.business.user.OrganizationService;
import pwd.initializr.organization.business.user.bo.Organization;
import pwd.initializr.organization.business.user.bo.OrganizationMember;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 18:11
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
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
  private OrganizationMemberService organizationMemberService;

  @Autowired
  private OrganizationService organizationService;

  @ApiOperation(value = "组织列表")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listOrgByMemId() {
    ObjectList<OrganizationMember> organizationMemberObjectList = organizationMemberService
        .findMyJoined(getUid(), null);
    List<Long> orgIds = new ArrayList<>();
    for (OrganizationMember element : organizationMemberObjectList.getElements()) {
      orgIds.add(element.getOrgId());
    }
    ObjectList<Organization> organizationObjectList = organizationService
        .listById(orgIds.toArray(new Long[]{}), null);
    Map<Long, Organization> temp = new HashMap<>();
    for (Organization organization : organizationObjectList.getElements()) {
      temp.put(organization.getId(), organization);
    }
    ObjectList<ListMineOrgOutput> result = new ObjectList<>();
    for (OrganizationMember organizationMember : organizationMemberObjectList.getElements()) {
      ListMineOrgOutput listMineOrgOutput = new ListMineOrgOutput();
      BeanUtils.copyProperties(temp.get(organizationMember.getOrgId()), listMineOrgOutput);
      listMineOrgOutput.setMyLevel(organizationMember.getLevel());
      listMineOrgOutput.setMyStatus(organizationMember.getStatus());
      result.getElements().add(listMineOrgOutput);
    }
    outputData(result);
  }


  @ApiOperation(value = "创建组织")
  @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void create(@RequestBody CreateOrgInput input) {
    ObjectList<OrganizationMember> myCreation = organizationMemberService
        .findMyCreation(getUid(), null);
    if (myCreation != null && myCreation.getElements() != null
        && myCreation.getElements().size() > 0) {
      // 已经创建过一个组织
      outputData(400);
    } else {
      ObjectList<OrganizationMember> myJoined = organizationMemberService
          .findMyJoined(getUid(), null);
      if (myJoined != null && myJoined.getElements() != null
          && myJoined.getElements().size() > 0) {
        Organization organization = new Organization();
        organization.setPid(myJoined.getElements().get(0).getOrgId());
        BeanUtils.copyProperties(input, organization);
        OrganizationMember organizationMember = new OrganizationMember();
        organizationMember.setMemId(getUid());
        organizationService.create(organization, organizationMember);
        CreateOrgOutput createOrgOutput = new CreateOrgOutput();
        BeanUtils.copyProperties(organization, createOrgOutput);
        outputData(createOrgOutput);
      } else {
        outputData(400);// TODO 还没有加入组织
      }
    }
  }




}
