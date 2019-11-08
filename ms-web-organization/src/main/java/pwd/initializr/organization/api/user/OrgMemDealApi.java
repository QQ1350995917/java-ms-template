package pwd.initializr.organization.api.user;

import io.swagger.annotations.Api;

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
@Api(
    tags = "组织用户关系管理",
    value = "orgDealApi",
    description = "[组织发出邀请，组织发出的邀请列表，组织收到的申请列表，用户发出申请，用户发出的申请列表，用户收到的邀请列表，成为组织成员]"
)
public interface OrgMemDealApi {

  void invite(Long userId);

  void invitationInOrg(Long orgId);

  void applicationInOrg(Long orgId);

  void apply(Long orgId);

  void applicationInUser(Long userId);

  void invitationInUser(Long userId);

  void deal(Long dealId);
}
