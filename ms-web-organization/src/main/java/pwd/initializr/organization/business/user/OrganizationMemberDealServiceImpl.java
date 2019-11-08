package pwd.initializr.organization.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.OrganizationMemberDeal;
import pwd.initializr.organization.persistence.dao.OrganizationMemberDealEntity;
import pwd.initializr.organization.persistence.mapper.OrganizationMemberDealMapper;

/**
 * pwd.initializr.organization.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-07 22:52
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class OrganizationMemberDealServiceImpl implements OrganizationMemberDealService {

  @Autowired
  private OrganizationMemberDealMapper organizationMemberDealMapper;

  @Override
  public OrganizationMemberDeal findOneById(Long id) {
    OrganizationMemberDealEntity organizationMemberDealEntity = organizationMemberDealMapper
        .findOneById(id);
    OrganizationMemberDeal organizationMemberDeal = new OrganizationMemberDeal();
    BeanUtils.copyProperties(organizationMemberDealEntity, organizationMemberDeal);
    return organizationMemberDeal;
  }

  @Override
  public OrganizationMemberDeal findOneByOrgIdUserIdType(Long orgId, Long userId, Integer type) {
    OrganizationMemberDealEntity organizationMemberDealEntity = organizationMemberDealMapper
        .findOneByOrgIdUserIdType(orgId, userId, type);
    OrganizationMemberDeal organizationMemberDeal = new OrganizationMemberDeal();
    BeanUtils.copyProperties(organizationMemberDealEntity, organizationMemberDeal);
    return organizationMemberDeal;
  }


  @Override
  public void create(OrganizationMemberDeal organizationMemberDeal) {
    OrganizationMemberDealEntity organizationMemberDealEntity = new OrganizationMemberDealEntity();
    BeanUtils.copyProperties(organizationMemberDeal, organizationMemberDealEntity);
    organizationMemberDealEntity.setDeal(0);
    organizationMemberDealEntity.setCounter(1);
    organizationMemberDealEntity.setStatus(0);
    organizationMemberDealEntity.setCreateTime(System.currentTimeMillis());
    organizationMemberDealEntity.setUpdateTime(System.currentTimeMillis());
    organizationMemberDealMapper.create(organizationMemberDealEntity);
  }


  @Override
  public void updateCounterById(Long orgId, Long userId, Integer type) {
    organizationMemberDealMapper.updateCounter(orgId, userId, type);
  }


  @Override
  public ObjectList<OrganizationMemberDeal> listByOrgId(Long orgId, Integer type) {
    List<OrganizationMemberDealEntity> organizationMemberDealEntities = organizationMemberDealMapper
        .listByOrgId(orgId, type);
    ObjectList<OrganizationMemberDeal> result = new ObjectList<>();
    for (OrganizationMemberDealEntity organizationMemberDealEntity : organizationMemberDealEntities) {
      OrganizationMemberDeal organizationMemberDeal = new OrganizationMemberDeal();
      BeanUtils.copyProperties(organizationMemberDealEntity, organizationMemberDeal);
      result.getElements().add(organizationMemberDeal);
    }
    return result;
  }


  @Override
  public ObjectList<OrganizationMemberDeal> listByUserId(Long userId, Integer type) {
    List<OrganizationMemberDealEntity> organizationMemberDealEntities = organizationMemberDealMapper
        .listByUserId(userId, type);
    ObjectList<OrganizationMemberDeal> result = new ObjectList<>();
    for (OrganizationMemberDealEntity organizationMemberDealEntity : organizationMemberDealEntities) {
      OrganizationMemberDeal organizationMemberDeal = new OrganizationMemberDeal();
      BeanUtils.copyProperties(organizationMemberDealEntity, organizationMemberDeal);
      result.getElements().add(organizationMemberDeal);
    }
    return result;
  }

  @Override
  public void deal(Long dealId) {
    organizationMemberDealMapper.updateToDeal(dealId);
  }
}
