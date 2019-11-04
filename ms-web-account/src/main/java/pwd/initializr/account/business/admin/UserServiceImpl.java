package pwd.initializr.account.business.admin;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.User;
import pwd.initializr.account.persistence.dao.UserEntity;
import pwd.initializr.account.persistence.mapper.UserMapper;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 18:38
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
  public ObjectList<User> listUser() {
    List<UserEntity> userEntities = userMapper.listUser();
    ObjectList<User> result = new ObjectList<>();
    for (UserEntity userEntity : userEntities) {
      User user = new User();
      BeanUtils.copyProperties(userEntity,user);
      result.getElements().add(user);
    }
    return result;
  }

  @Override
  public User findById(Long userId) {
    UserEntity userEntity = userMapper.findByUserId(userId);
    User user = new User();
    BeanUtils.copyProperties(userEntity,user);
    return user;
  }

  @Override
  public ObjectList<User> listUserById(String[] ids, Integer status) {
    List<UserEntity> userEntities = userMapper.listUser();
    ObjectList<User> result = new ObjectList<>();
    for (UserEntity userEntity : userEntities) {
      User user = new User();
      BeanUtils.copyProperties(userEntity,user);
      result.getElements().add(user);
    }
    return result;
  }
}
