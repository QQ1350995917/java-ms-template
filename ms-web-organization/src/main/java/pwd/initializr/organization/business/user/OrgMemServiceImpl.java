package pwd.initializr.organization.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.OrgMem;
import pwd.initializr.organization.persistence.dao.OrgMemEntity;
import pwd.initializr.organization.persistence.mapper.OrganizationMemberMapper;

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
public class OrgMemServiceImpl implements OrgMemService {

  @Autowired
  private OrganizationMemberMapper organizationMemberMapper;

  @Override
  public void addUserToOrg(OrgMem userOrg) {
    OrgMemEntity userOrgEntity = new OrgMemEntity();
    BeanUtils.copyProperties(userOrg,userOrgEntity);
    userOrgEntity.setCreateTime(System.currentTimeMillis());
    userOrgEntity.setUpdateTime(System.currentTimeMillis());
    organizationMemberMapper.addMemToOrg(userOrgEntity);
  }

  @Override
  public void updateLevel(Long userId, Long orgId, Integer level) {
    organizationMemberMapper.updateMemLevelInOrg(userId,orgId,level);
  }

  @Override
  public void updateStatus(Long userId, Long orgId, Integer status) {
    organizationMemberMapper.updateMemStatusInOrg(userId,orgId,status);
  }

  @Override
  public OrgMem findOne(Long userId, Long orgId) {
    OrgMemEntity userOrgEntity = organizationMemberMapper.findOneMemInOrg(userId, orgId);
    OrgMem userOrg = new OrgMem();
    BeanUtils.copyProperties(userOrgEntity,userOrg);
    return userOrg;
  }

  @Override
  public ObjectList<OrgMem> listByOrgId(Long orgId, Integer status) {
    ObjectList<OrgMem> result= new ObjectList<>();
    List<OrgMemEntity> userOrgEntities = organizationMemberMapper.listMemInOrgByOrgId(orgId, status);
    for (OrgMemEntity userOrgEntity : userOrgEntities) {
      OrgMem userOrg = new OrgMem();
      BeanUtils.copyProperties(userOrgEntity,userOrg);
      result.getElements().add(userOrg);
    }
    return result;
  }

  @Override
  public ObjectList<OrgMem> listByUserId(Long userId, Integer status) {
    ObjectList<OrgMem> result= new ObjectList<>();
    List<OrgMemEntity> userOrgEntities = organizationMemberMapper.listOrgContainMemByMemId(userId, status);
    for (OrgMemEntity userOrgEntity : userOrgEntities) {
      OrgMem userOrg = new OrgMem();
      BeanUtils.copyProperties(userOrgEntity,userOrg);
      result.getElements().add(userOrg);
    }
    return result;
  }
}
