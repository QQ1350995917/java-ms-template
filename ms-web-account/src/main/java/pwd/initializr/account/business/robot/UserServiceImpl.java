package pwd.initializr.account.business.robot;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.robot.bo.User;

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

//  @Autowired
//  private UserMapperBak userMapper;

  @Override
  public List<User> listByUserId(Long[] userIds) {
//    List<UserEntity> userEntities = userMapper.listUserByIds(userIds);
    List<User> users = new LinkedList<>();
//    for (UserEntity userEntity : userEntities) {
//      UserBO user = new UserBO();
//      BeanUtils.copyProperties(userEntity, user);
//      users.add(user);
//    }
    return users;
  }
}
