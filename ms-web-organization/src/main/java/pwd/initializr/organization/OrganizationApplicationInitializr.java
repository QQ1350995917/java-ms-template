package pwd.initializr.organization;

import java.util.LinkedHashSet;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.organization.business.OrganizationService;
import pwd.initializr.organization.business.bo.OrganizationBO;

/**
 * pwd.initializr.organization@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-22 21:36
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Slf4j
public class OrganizationApplicationInitializr {

  @Resource
  private OrganizationService organizationService;

  @PostConstruct
  @Transactional(rollbackFor = RuntimeException.class)
  public void initializr() {
    PageableQueryResult<OrganizationBO> organizationBOPageableQueryResult = organizationService
        .queryAllByCondition(new LinkedHashSet<>(), null, 0L, 1L);
    if (organizationBOPageableQueryResult.getTotal() == null
        || organizationBOPageableQueryResult.getTotal() < 1) {
      String[] cxos = {"CEO办", "CFO办", "CHO办", "CMO办", "CTO办"};
      for (int i = 0; i < cxos.length; i++) {
        String cxo = cxos[i];
        OrganizationBO organizationBO = new OrganizationBO();
        organizationBO.setPid(0L);
        organizationBO.setName(cxo);
        organizationBO.setSort(i);
        organizationBO.setMembers(0);
        organizationService.insert(organizationBO);
      }
    }
  }
}
