package pwd.initializr.account.business.robot;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.robot.bo.User;
import pwd.initializr.account.persistence.dao.UserEntity;
import pwd.initializr.account.persistence.mapper.UserMapper;

/**
 * pwd.initializr.account.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 15:38
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserMapper userMapper;

  @Override
  public List<User> listByUserId(Long[] userIds) {
    List<UserEntity> userEntities = userMapper.listUserByIds(userIds);
    List<User> users = new LinkedList<>();
    for (UserEntity userEntity : userEntities) {
      User user = new User();
      BeanUtils.copyProperties(userEntity,user);
      users.add(user);
    }
    return users;
  }
}
