package pwd.initializr.organization.api.user;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.OrganizationMemberDealService;
import pwd.initializr.organization.business.user.OrganizationMemberService;
import pwd.initializr.organization.business.user.bo.OrganizationMember;
import pwd.initializr.organization.business.user.bo.OrganizationMemberDeal;

/**
 * pwd.initializr.organization.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-08 12:34
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestController(value = "orgDealApi")
@RequestMapping(value = "/api/org/deal")
public class OrgMemDealController extends UserController implements OrgMemDealApi {

  @Autowired
  private OrganizationMemberDealService organizationMemberDealService;
  @Autowired
  private OrganizationMemberService organizationMemberService;

  @ApiImplicitParams({
      @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", example = "1", paramType = "path")
  })
  @ApiOperation(value = "组织发出邀请")
  @PutMapping(value = {"/invite/{userId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void invite(@PathVariable("userId") Long userId) {
    OrganizationMemberDeal organizationMemberDeal = organizationMemberDealService
        .findOneByOrgIdUserIdType(1L, userId, 0);// TODO 根据当前token查出userId，查出所在的orgId，枚举化type
    if (organizationMemberDeal != null) {
      organizationMemberDealService
          .updateCounterById(1L, userId, 0);// TODO 根据当前token查出userId，查出所在的orgId，枚举化type
      outputData();
    } else {
      OrganizationMemberDeal organizationMemberDeal1 = new OrganizationMemberDeal();
      // TODO 根据当前token查出userId，查出所在的orgId，枚举化type
      organizationMemberDeal1.setOrgId(1L);
      organizationMemberDeal1.setUserId(userId);
      organizationMemberDeal1.setType(0);
      organizationMemberDealService.create(organizationMemberDeal1);
    }
  }

  @ApiImplicitParams({
      @ApiImplicitParam(name = "orgId", value = "组织ID", required = true, dataType = "Long", example = "1", paramType = "path")
  })
  @ApiOperation(value = "组织发出的邀请列表，组织收到的申请列表")
  @GetMapping(value = {"/invitation/{orgId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void invitation(@PathVariable(value = "orgId") Long orgId,
      @PathVariable(value = "type") Integer type) {
    ObjectList<OrganizationMemberDeal> organizationMemberDealObjectList = organizationMemberDealService
        .listByOrgId(orgId, type);
    outputData(organizationMemberDealObjectList);
  }

  @ApiImplicitParams({
      @ApiImplicitParam(name = "orgId", value = "组织ID", required = true, dataType = "Long", example = "1", paramType = "path")
  })
  @ApiOperation(value = "用户发出申请")
  @PutMapping(value = {"/apply/{orgId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void apply(@PathVariable("orgId") Long orgId) {
    OrganizationMemberDeal organizationMemberDeal = organizationMemberDealService
        .findOneByOrgIdUserIdType(orgId, 1L, 1);// TODO 根据当前token查出userId，枚举化type
    if (organizationMemberDeal != null) {
      organizationMemberDealService
          .updateCounterById(orgId, 1L, 1);// TODO 根据当前token查出userId，枚举化type
      outputData();
    } else {
      OrganizationMemberDeal organizationMemberDeal1 = new OrganizationMemberDeal();
      // TODO 根据当前token查出userId，枚举化type
      organizationMemberDeal1.setOrgId(orgId);
      organizationMemberDeal1.setUserId(1L);
      organizationMemberDeal1.setType(1);
      organizationMemberDealService.create(organizationMemberDeal1);
    }
  }


  @ApiImplicitParams({
      @ApiImplicitParam(name = "orgId", value = "组织ID", required = true, dataType = "Long", example = "1", paramType = "path")
  })
  @ApiOperation(value = "用户收到的邀请列表，用户发出的申请列表")
  @GetMapping(value = {"/application/{orgId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void application(@PathVariable(value = "orgId") Long userId,
      @PathVariable(value = "type") Integer type) {
    ObjectList<OrganizationMemberDeal> organizationMemberDealObjectList = organizationMemberDealService
        .listByUserId(userId, type);
    outputData(organizationMemberDealObjectList);
  }

  @ApiOperation(value = "成为组织成员")
  @PutMapping(value = {"/{dealId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void deal(@PathVariable("dealId") Long dealId) {
    OrganizationMemberDeal oneById = organizationMemberDealService.findOneById(dealId);
    if (oneById.getType() == 0) {
      // 组织同意申请
      // TODO 找到当前用户所创建的组织的id比对
      ObjectList<OrganizationMember> myCreation = organizationMemberService
          .findMyCreation(1L, null);
      if (myCreation == null || myCreation.getElements() == null
          || myCreation.getElements().size() == 0) {
        outputException(400);
      } else {
        if (oneById.getOrgId() == myCreation.getElements().get(0).getOrgId()) {
          organizationMemberDealService.deal(dealId);
        } else {
          outputException(400);
        }
      }
    } else if (oneById.getType() == 1L) {
      // 用户同意邀请
      // TODO 找到当前用户的id比对
      if (oneById.getUserId() == 1L) {
        organizationMemberDealService.deal(dealId);
      } else {
        outputException(400);
      }
    }
  }
}
