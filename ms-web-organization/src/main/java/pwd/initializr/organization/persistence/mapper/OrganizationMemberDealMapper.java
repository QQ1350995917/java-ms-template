package pwd.initializr.organization.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.organization.persistence.dao.OrganizationMemberDealEntity;

/**
 * pwd.initializr.organization.persistence.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-07 12:56
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Mapper
public interface OrganizationMemberDealMapper {

  OrganizationMemberDealEntity findOneByOrgIdUserIdType(@Param("orgId") Long orgId,
      @Param("userId") Long userId, @Param("type") Integer type);

  void create(OrganizationMemberDealEntity organizationMemberDealEntity);

  void updateCounter(@Param("orgId") Long orgId,
      @Param("userId") Long userId, @Param("type") Integer type);

  List<OrganizationMemberDealEntity> listByOrgId(@Param("orgId") Long orgId,
      @Param("type") Integer type);

  List<OrganizationMemberDealEntity> listByUserId(@Param("userId") Long userId,
      @Param("type") Integer type);
}
