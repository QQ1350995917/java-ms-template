package pwd.initializr.organization.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;

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
@RequestMapping(value = "/api/org/")
public class OrgMemDealController extends UserController implements OrgMemDealApi {

  @ApiOperation(value = "组织发出邀请")
  @PutMapping(value = {"/invite/{userId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void invite(@PathVariable("userId") Long userId) {

  }

  @ApiOperation(value = "组织发出的邀请列表")
  @GetMapping(value = {"/invitation/{orgId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void invitationInOrg(@PathVariable("orgId")Long orgId) {

  }

  @ApiOperation(value = "组织收到的申请列表")
  @GetMapping(value = {"/application/{orgId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void applicationInOrg(@PathVariable("orgId")Long orgId) {

  }

  @ApiOperation(value = "用户发出申请")
  @PutMapping(value = {"/user/apply/{orgId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void apply(@PathVariable("orgId") Long orgId) {

  }

  @ApiOperation(value = "用户发出的申请列表")
  @GetMapping(value = {"/user/application/{userId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void applicationInUser(@PathVariable("userId") Long userId) {

  }

  @ApiOperation(value = "用户收到的邀请列表")
  @GetMapping(value = {"/user/invitation/{userId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void invitationInUser(@PathVariable("userId") Long userId) {

  }

  @ApiOperation(value = "成为组织成员")
  @PutMapping(value = {"/{dealId}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void deal(@PathVariable("dealId") Long dealId) {

  }
}
