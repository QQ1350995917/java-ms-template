package pwd.initializr.email;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.email.business.EmailService;

/**
 * pwd.initializr.account@ms-web-initializr
 *
 * <h1>系统业务自检</h1>
 *
 * date 2020-07-25 10:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Slf4j
public class EmailApplicationInitializr {

  @PostConstruct
  @Transactional(rollbackFor = RuntimeException.class)
  public void initializr() {

  }
}
