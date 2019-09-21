package pwd.initializr.account.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.persistence.dao.UserEntity;
import pwd.initializr.account.persistence.mapper.UserMapper;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 14:07
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
  public User insert(User user) {
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(user,userEntity);
    userMapper.insertUser(userEntity);
    user.setId(userEntity.getId());
    return user;
  }

  @Override
  public ObjectList<User> listUser() {
    ObjectList<User> result = new ObjectList<>();
    List<UserEntity> userEntities = userMapper.listUser();
    for (UserEntity userEntity : userEntities) {
      User user = new User();
      BeanUtils.copyProperties(userEntity,user);
      result.getElements().add(user);
    }
    return result;
  }

  @Override
  public User findByUserId(Long userId) {
    UserEntity userEntity = userMapper.findByUserId(userId);
    User user = new User();
    BeanUtils.copyProperties(userEntity,user);
    return user;
  }

  @Override
  public Long countUser() {
    Long countUser = userMapper.countUser();
    return countUser;
  }
}
