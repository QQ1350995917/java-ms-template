package pwd.initializr.organization.business.remote;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pwd.initializr.account.rpc.RPCUser;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.organization.FeignConfig;

/**
 * pwd.initializr.organization.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-06-14 13:55
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@FeignClient(value = "account", configuration = FeignConfig.class, fallback = OrganizationMemberClientServiceFallbackImpl.class)
public interface OrganizationMemberClientService {

  @RequestMapping(value = "/api/robot/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  Output<List<RPCUser>> findByIds(@RequestParam("ids") Long[] ids);
}
