package pwd.initializr.account.business.robot;

import java.util.List;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.robot.bo.UserBO;

/**
 * pwd.initializr.account.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 15:36
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface UserService {

  List<UserBO> listByUserId(Long[] userIds);
}
