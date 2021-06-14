package pwd.initializr.account.business.robot;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.robot.bo.UserBO;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.account.persistence.dao.UserUserDao;
import pwd.initializr.account.persistence.entity.UserUserEntity;

/**
 * pwd.initializr.account.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 15:38
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserUserDao userUserDao;

  @Override
  public List<UserBO> listByUserId(Long[] userIds) {
    List<UserUserEntity> userEntities = userUserDao.queryByIds(userIds);
    List<UserBO> collect = userEntities.stream().map(this::convertUserEntity2UserBO)
        .collect(Collectors.toList());
    return collect;
  }

  private UserBO convertUserEntity2UserBO(UserUserEntity entity) {
    UserBO user = new UserBO();
    BeanUtils.copyProperties(entity, user);
    return user;
  }
}
