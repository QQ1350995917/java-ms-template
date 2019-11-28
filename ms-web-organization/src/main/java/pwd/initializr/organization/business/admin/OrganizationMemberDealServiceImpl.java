package pwd.initializr.organization.business.admin;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.admin.bo.OrganizationMemberDeal;
import pwd.initializr.organization.persistence.dao.OrganizationMemberDealEntity;
import pwd.initializr.organization.persistence.mapper.OrganizationMemberDealMapper;

/**
 * pwd.initializr.organization.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-08 18:37
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class OrganizationMemberDealServiceImpl implements OrganizationMemberDealService {

  @Autowired
  private OrganizationMemberDealMapper organizationMemberDealMapper;

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
}