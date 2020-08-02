package pwd.initializr.organization.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.organization.business.user.bo.OrganizationMember;
import pwd.initializr.organization.persistence.dao.OrganizationMemberEntity;
import pwd.initializr.organization.persistence.mapper.OrganizationMemberMapper;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-28 23:15
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class OrganizationMemberServiceImpl implements OrganizationMemberService {

  @Autowired
  private OrganizationMemberMapper organizationMemberMapper;

  @Override
  public void addMemToOrg(OrganizationMember organizationMember) {
    OrganizationMemberEntity userOrgEntity = new OrganizationMemberEntity();
    BeanUtils.copyProperties(organizationMember, userOrgEntity);
    userOrgEntity.setCreateTime(System.currentTimeMillis());
    userOrgEntity.setUpdateTime(System.currentTimeMillis());
    organizationMemberMapper.addMemToOrg(userOrgEntity);
  }


  @Override
  public PageableQueryResult<OrganizationMember> findMyCreation(Long memId, Integer status) {
    PageableQueryResult<OrganizationMember> result = new PageableQueryResult<>();
    List<OrganizationMemberEntity> userOrgEntities = organizationMemberMapper
        .findMyCreation(memId, status);
    for (OrganizationMemberEntity userOrgEntity : userOrgEntities) {
      OrganizationMember userOrg = new OrganizationMember();
      BeanUtils.copyProperties(userOrgEntity, userOrg);
      result.getElements().add(userOrg);
    }
    return result;
  }


  @Override
  public PageableQueryResult<OrganizationMember> findMyJoined(Long memId, java.lang.Integer status) {
    PageableQueryResult<OrganizationMember> result = new PageableQueryResult<>();
    List<OrganizationMemberEntity> userOrgEntities = organizationMemberMapper
        .findMyJoined(memId, status);
    for (OrganizationMemberEntity userOrgEntity : userOrgEntities) {
      OrganizationMember userOrg = new OrganizationMember();
      BeanUtils.copyProperties(userOrgEntity, userOrg);
      result.getElements().add(userOrg);
    }
    return result;
  }


  @Override
  public OrganizationMember findOneMemInOrg(Long orgId, Long memId) {
    OrganizationMemberEntity userOrgEntity = organizationMemberMapper.findOneMemInOrg(orgId, memId);
    OrganizationMember userOrg = new OrganizationMember();
    BeanUtils.copyProperties(userOrgEntity, userOrg);
    return userOrg;
  }


  @Override
  public PageableQueryResult<OrganizationMember> listByOrgId(Long orgId, Integer status) {
    PageableQueryResult<OrganizationMember> result = new PageableQueryResult<>();
    List<OrganizationMemberEntity> userOrgEntities = organizationMemberMapper
        .listMemInOrgByOrgId(orgId, status);
    for (OrganizationMemberEntity userOrgEntity : userOrgEntities) {
      OrganizationMember userOrg = new OrganizationMember();
      BeanUtils.copyProperties(userOrgEntity, userOrg);
      result.getElements().add(userOrg);
    }
    return result;
  }


  @Override
  public void updateMemLevel(Long orgId, Long memId, Integer level) {
    organizationMemberMapper.updateMemLevelInOrg(orgId, memId, level);
  }


  @Override
  public void updateMemStatus(Long orgId, Long memId, Integer status) {
    organizationMemberMapper.updateMemStatusInOrg(memId, orgId, status);
  }


}
