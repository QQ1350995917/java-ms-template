package pwd.initializr.organization.business.user;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.organization.business.user.OrganizationMemberInfoServiceImpl.OrganizationMemberInfoServiceFetcher.OrganizationMemberInfoServiceFetcherImpl;
import pwd.initializr.organization.business.user.bo.OrganizationMemberInfo;

/**
 * pwd.initializr.organization.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 16:37
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class OrganizationMemberInfoServiceImpl implements OrganizationMemberInfoService {

  @Autowired
  private OrganizationMemberInfoServiceFetcher organizationMemberInfoServiceFetcher;

  @Override
  public List<OrganizationMemberInfo> fetchMemberInfo(Long[] memberIds) {
    Output<List<OrganizationMemberInfo>> listOutput = organizationMemberInfoServiceFetcher
        .fetchMemberInfo(memberIds);
    if (listOutput.getMeta().getCode() == 200) {
      return listOutput.getData();
    } else {
      return Collections.emptyList();
    }
  }

  @Component
  @FeignClient(value = "${ms.account.name}", fallback = OrganizationMemberInfoServiceFetcherImpl.class)
  interface OrganizationMemberInfoServiceFetcher {

    @RequestMapping(value = "/api/robot/user", method = RequestMethod.GET)
    Output<List<OrganizationMemberInfo>> fetchMemberInfo(
        @RequestParam(value = "userIds") Long[] userIds);

    @Component
    class OrganizationMemberInfoServiceFetcherImpl implements OrganizationMemberInfoServiceFetcher {

      @Override
      public Output<List<OrganizationMemberInfo>> fetchMemberInfo(Long[] userIds) {
        return new Output<>(new Meta(400));
      }
    }
  }
}
