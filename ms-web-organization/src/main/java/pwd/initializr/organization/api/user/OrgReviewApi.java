package pwd.initializr.organization.api.user;

import pwd.initializr.organization.api.user.vo.ReviewPendingInput;

/**
 * pwd.initializr.organization.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 14:35
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrgReviewApi {

  void listReviewPending(Long orgId);

  void reviewPending(ReviewPendingInput input);
}
