package pwd.initializr.access;

import java.util.LinkedHashSet;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.access.business.admin.RoleService;
import pwd.initializr.access.business.admin.bo.RoleBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;

/**
 * pwd.initializr.access@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-22 23:40
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Slf4j
public class AccessApplicationInitializr {
  @Resource
  private RoleService roleService;

  @PostConstruct
  @Transactional(rollbackFor = RuntimeException.class)
  public void initializr() {
    PageableQueryResult<RoleBO> roleBOPageableQueryResult = roleService
        .queryAllByCondition(new LinkedHashSet<>(), null, 0L, 1L);
    if (roleBOPageableQueryResult.getTotal() == null
        || roleBOPageableQueryResult.getTotal() < 1) {
      String[] cxos = {"CEO", "CFO", "CHO", "CMO", "CTO"};
      for (int i = 0; i < cxos.length; i++) {
        String cxo = cxos[i];
        RoleBO roleBO = new RoleBO();
        roleBO.setPid(0L);
        roleBO.setName(cxo);
        roleBO.setSummary(cxo);
        roleBO.setOrder(i);
        roleBO.setVersion(0L);
        roleService.insert(roleBO);
      }
    }
  }
}
