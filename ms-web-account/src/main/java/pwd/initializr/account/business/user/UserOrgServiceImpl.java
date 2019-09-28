package pwd.initializr.account.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.UserOrg;
import pwd.initializr.account.persistence.dao.UserOrgEntity;
import pwd.initializr.account.persistence.mapper.UserOrgMapper;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-28 23:15
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class UserOrgServiceImpl implements UserOrgService {

  @Autowired
  private UserOrgMapper userOrgMapper;

  @Override
  public void addUserToOrg(UserOrg userOrg) {
    UserOrgEntity userOrgEntity = new UserOrgEntity();
    BeanUtils.copyProperties(userOrg,userOrgEntity);
    userOrgEntity.setCreateTime(System.currentTimeMillis());
    userOrgEntity.setUpdateTime(System.currentTimeMillis());
    userOrgMapper.addUserToOrg(userOrgEntity);
  }

  @Override
  public void updateLevel(Long userId, Long orgId, Integer level) {
    userOrgMapper.updateLevel(userId,orgId,level);
  }

  @Override
  public void updateStatus(Long userId, Long orgId, Integer status) {
    userOrgMapper.updateStatus(userId,orgId,status);
  }

  @Override
  public UserOrg findOne(Long userId, Long orgId) {
    UserOrgEntity userOrgEntity = userOrgMapper.findOne(userId, orgId);
    UserOrg userOrg = new UserOrg();
    BeanUtils.copyProperties(userOrgEntity,userOrg);
    return userOrg;
  }

  @Override
  public ObjectList<UserOrg> listByOrgId(Long orgId, Integer status) {
    ObjectList<UserOrg> result= new ObjectList<>();
    List<UserOrgEntity> userOrgEntities = userOrgMapper.listByOrgId(orgId, status);
    for (UserOrgEntity userOrgEntity : userOrgEntities) {
      UserOrg userOrg = new UserOrg();
      BeanUtils.copyProperties(userOrgEntity,userOrg);
      result.getElements().add(userOrg);
    }
    return result;
  }

  @Override
  public ObjectList<UserOrg> listByUserId(Long userId, Integer status) {
    ObjectList<UserOrg> result= new ObjectList<>();
    List<UserOrgEntity> userOrgEntities = userOrgMapper.listByUserId(userId, status);
    for (UserOrgEntity userOrgEntity : userOrgEntities) {
      UserOrg userOrg = new UserOrg();
      BeanUtils.copyProperties(userOrgEntity,userOrg);
      result.getElements().add(userOrg);
    }
    return result;
  }
}
