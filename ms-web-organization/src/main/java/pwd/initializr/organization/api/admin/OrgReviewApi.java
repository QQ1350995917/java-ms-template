package pwd.initializr.organization.api.admin;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.organization.api.admin.vo.OrgReviewInput;

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
public interface OrgReviewApi {

  void listReview(Long orgId);

  void listReviewOption();

  void review(OrgReviewInput input);


}
