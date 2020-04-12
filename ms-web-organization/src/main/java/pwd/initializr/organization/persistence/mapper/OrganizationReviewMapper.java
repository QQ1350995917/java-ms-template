package pwd.initializr.organization.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.organization.persistence.dao.OrganizationReviewEntity;

/**
 * pwd.initializr.organization.persistence.entity@ms-web-initializr
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
public interface OrganizationReviewMapper {

  /**
   * <h2>创建组织审核记录</h2>
   * date 2019-11-03 21:57
   *
   * @param organizationProgressEntity 组织审核记录
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void create(OrganizationReviewEntity organizationProgressEntity);


  /**
   * <h2>查询组织下的审核记录</h2>
   * date 2019-11-03 21:56
   *
   * @param orgId 组织ID
   * @param status 进度状态
   * @return java.util.List<pwd.initializr.organization.persistence.dao.OrganizationReviewEntity>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<OrganizationReviewEntity> listByOrgId(@Param("orgId") Long orgId,
      @Param("status") Integer status);

  /*
   *
   * <h2>查询处于我的行进中的数据</h2>
   * date 2019-11-04
   *
   * @param editorId 数据提交人的ID
   * @param status 数据状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<OrganizationReviewEntity> listMyExecution(@Param("editorId") Long editorId,
      @Param("status") Integer status);
}
