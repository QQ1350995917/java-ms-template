package pwd.initializr.organization.api.admin;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.admin.OrganizationMemberDealService;
import pwd.initializr.organization.business.admin.bo.OrganizationMemberDeal;

/**
 * pwd.initializr.organization.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-08 18:41
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestController(value = "orgDealAdminApi")
@RequestMapping(value = "/api/admin/org/deal")
public class OrgMemDealController extends AdminController implements OrgMemDealApi {

  @Autowired
  private OrganizationMemberDealService organizationMemberDealService;

  @ApiImplicitParams({
      @ApiImplicitParam(name = "orgId", value = "组织ID", required = true, dataType = "Long", example = "1", paramType = "path"),
      @ApiImplicitParam(name = "type", value = "0：组织发出的邀请；1：组织收到的申请", required = true, dataType = "Integer", example = "1", paramType = "path")
  })
  @ApiOperation(value = "组织发出的邀请列表，组织收到的申请列表")
  @GetMapping(value = {
      "/invitation/{orgId}/{type}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void invitation(@PathVariable(value = "orgId") Long orgId,
      @PathVariable(value = "type") Integer type) {
    ObjectList<OrganizationMemberDeal> organizationMemberDealObjectList = organizationMemberDealService
        .listByOrgId(orgId, type);
    outputData(organizationMemberDealObjectList);
  }


  @ApiImplicitParams({
      @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", example = "1", paramType = "path"),
      @ApiImplicitParam(name = "type", value = "0：组织发出的邀请；1：组织收到的申请", required = true, dataType = "Integer", example = "1", paramType = "path")
  })
  @ApiOperation(value = "用户收到的邀请列表，用户发出的申请列表")
  @GetMapping(value = {
      "/application/{userId}/{type}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void application(@PathVariable(value = "userId") Long userId,
      @PathVariable(value = "type") Integer type) {
    ObjectList<OrganizationMemberDeal> organizationMemberDealObjectList = organizationMemberDealService
        .listByUserId(userId, type);
    outputData(organizationMemberDealObjectList);
  }
}
