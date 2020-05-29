package pwd.initializr.account.business.admin;

import org.springframework.stereotype.Service;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 18:38
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class UserServiceImpl implements UserService {

//  @Autowired
//  private UserMapperBak userMapper;
//
//  @Override
//  public ObjectList<UserBO> listUser() {
//    List<UserEntity> userEntities = userMapper.listUser();
//    ObjectList<UserBO> result = new ObjectList<>();
//    for (UserEntity userEntity : userEntities) {
//      UserBO user = new UserBO();
//      BeanUtils.copyProperties(userEntity,user);
//      result.getElements().add(user);
//    }
//    return result;
//  }
//
//  @Override
//  public UserBO findById(Long userId) {
//    UserEntity userEntity = userMapper.findByUserId(userId);
//    UserBO user = new UserBO();
//    BeanUtils.copyProperties(userEntity,user);
//    return user;
//  }
//
//  @Override
//  public ObjectList<UserBO> listUserById(String[] ids, Integer status) {
//    List<UserEntity> userEntities = userMapper.listUser();
//    ObjectList<UserBO> result = new ObjectList<>();
//    for (UserEntity userEntity : userEntities) {
//      UserBO user = new UserBO();
//      BeanUtils.copyProperties(userEntity,user);
//      result.getElements().add(user);
//    }
//    return result;
//  }
}
