package pwd.initializr.organization.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.Organization;
import pwd.initializr.organization.business.user.bo.OrganizationMember;
import pwd.initializr.organization.business.user.bo.OrganizationMember.Level;
import pwd.initializr.organization.persistence.dao.OrganizationMemberEntity;
import pwd.initializr.organization.persistence.dao.OrganizationEntity;
import pwd.initializr.organization.persistence.dao.OrganizationEntity.Progress;
import pwd.initializr.organization.persistence.mapper.OrganizationMapper;
import pwd.initializr.organization.persistence.mapper.OrganizationMemberMapper;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 22:41
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

  @Autowired
  private OrganizationMapper organizationMapper;
  @Autowired
  private OrganizationMemberMapper organizationMemberMapper;

  @Override
  public void create(Organization organization, OrganizationMember organizationMember) {
    OrganizationEntity organizationEntity = new OrganizationEntity();
    BeanUtils.copyProperties(organization, organizationEntity);
    organizationEntity.setCreateTime(System.currentTimeMillis());
    organizationEntity.setUpdateTime(System.currentTimeMillis());
    organizationMapper.create(organizationEntity);
    organizationMember.setOrgId(organizationEntity.getId());
    organizationMember.setCreateTime(System.currentTimeMillis());
    organizationMember.setUpdateTime(System.currentTimeMillis());
    organizationMember.setLevel(Level.OWNER.value());
    OrganizationMemberEntity orgMemEntity = new OrganizationMemberEntity();
    BeanUtils.copyProperties(organizationMember, orgMemEntity);
    organizationMemberMapper.addMemToOrg(orgMemEntity);
  }


  @Override
  public void update(Organization organization) {
    OrganizationEntity organizationEntity = new OrganizationEntity();
    BeanUtils.copyProperties(organization, organizationEntity);
    organizationMapper.update(organizationEntity);
  }


  @Override
  public void reviewPending(Long id) {
    organizationMapper.updateProgressById(id, Progress.REVIEW_PENDING.value());
  }

  @Override
  public ObjectList<Organization> listById(Long[] ids, Integer status) {
    ObjectList<Organization> result = new ObjectList<>();
    List<OrganizationEntity> organizationEntities = organizationMapper
        .listByIdAndStatus(ids, status);
    for (OrganizationEntity organizationEntity : organizationEntities) {
      Organization organization = new Organization();
      BeanUtils.copyProperties(organizationEntity, organization);
      result.getElements().add(organization);
    }
    return result;
  }

  @Override
  public ObjectList<Organization> listByPid(Long pid, Integer status) {
    ObjectList<Organization> result = new ObjectList<>();
    List<OrganizationEntity> organizationEntities = organizationMapper
        .listByPidAndStatus(pid, status);
    for (OrganizationEntity organizationEntity : organizationEntities) {
      Organization organization = new Organization();
      BeanUtils.copyProperties(organizationEntity, organization);
      result.getElements().add(organization);
    }
    return result;
  }


}
