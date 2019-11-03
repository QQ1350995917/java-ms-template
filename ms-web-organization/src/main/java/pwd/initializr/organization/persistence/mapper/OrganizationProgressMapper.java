package pwd.initializr.organization.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.organization.persistence.dao.OrganizationProgressEntity;

/**
 * pwd.initializr.organization.persistence.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 17:33
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Mapper
public interface OrganizationProgressMapper {

  /**
   * <h2>更新组织审核信息</h2>
   * date 2019-11-03 21:57
   *
   * @param organizationProgressEntity 组织审核信息
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void auditById(OrganizationProgressEntity organizationProgressEntity);


  /**
   * <h2>创建组织审核记录</h2>
   * date 2019-11-03 21:57
   *
   * @param organizationProgressEntity 组织审核记录
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void create(OrganizationProgressEntity organizationProgressEntity);


  /**
   * <h2>查询组织下的审核记录</h2>
   * date 2019-11-03 21:56
   *
   * @param orgId 组织ID
   * @param status 进度状态
   * @return java.util.List<pwd.initializr.organization.persistence.dao.OrganizationProgressEntity>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<OrganizationProgressEntity> listByOrgId(@Param("orgId") Long orgId,
      @Param("status") Integer status);


  /**
   * <h2>更新组织审核进度</h2>
   * date 2019-09-28
   *
   * @param orgId 组织ID
   * @param auditorResult 审核进度
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateProgressById(Long orgId, Integer auditorResult);


}
