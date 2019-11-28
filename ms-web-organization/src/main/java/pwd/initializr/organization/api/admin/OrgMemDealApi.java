package pwd.initializr.organization.api.admin;

import io.swagger.annotations.Api;

/**
 * pwd.initializr.organization.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-08 18:40
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "组织用户关系管理",
    value = "orgDealApi",
    description = "[组织发出的邀请列表，组织收到的申请列表，用户收到的邀请列表，用户发出的申请列表]"
)
public interface OrgMemDealApi {

  void invitation(Long orgId, Integer type);

  void application(Long userId, Integer type);
}
