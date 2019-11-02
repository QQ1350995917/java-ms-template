package pwd.initializr.organization.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.organization.persistence.dao.OrganizationEntity;

/**
 * pwd.initializr.account.persistence.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 17:05
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Mapper
public interface OrganizationMapper {

  /*
   *
   * <h2>创建一个组织</h2>
   * date 2019-09-27
   *
   * @param organizationEntity 组织属性
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void create(OrganizationEntity organizationEntity);

  /*
   *
   * <h2>更新组织信息初始化可为空的信息</h2>
   * date 2019-09-27
   *
   * @param organizationEntity 组织可为空属性
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void update(OrganizationEntity organizationEntity);

  /*
   *
   * <h2>更新组织等级信息</h2>
   * date 2019-09-27
   *
   * @param id 组织ID
   * @param level 组织等级
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateLevelById(@Param("id") Long id, @Param("level") Integer level);

  /*
   *
   * <h2>更新下级组织排序信息</h2>
   * date 2019-09-27
   *
   * @param pid 上级组织ID
   * @param id 组织ID
   * @param sort 组织排序值
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateSortById(Long pid, Long id, Integer sort);

  /*
   *
   * <h2>更新组织成员数量</h2>
   * date 2019-09-27
   *
   * @param id 组织ID
   * @param members 组织成员数量
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateMembersById(Long id, Integer members);

  /*
   *
   * <h2>更新组织状态</h2>
   * date 2019-09-27
   *
   * @param id 组织ID
   * @param status 组织状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateStatusById(Long id, Integer status);

  /*
   *
   * <h2>更新组织审核</h2>
   * date 2019-09-28
   *
   * @param id 组织ID
   * @param progress 审核进度
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateProgressById(Long id, Integer progress);

  /**
   * <h2>通过ID查找</h2>
   * date 2019-11-02 09:57
   *
   * @param id 自主ID
   * @return pwd.initializr.account.persistence.dao.OrganizationEntity
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  OrganizationEntity findOneById(Long id);

  /*
   *
   * <h2>根据等级查询下级组织集</h2>
   * date 2019-09-27
   *
   * @param pid 上级组织ID
   * @param status 组织状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<OrganizationEntity> listByPidAndStatus(Long pid, Integer status);


  /*
   *
   * <h2>根据状态查询组织集</h2>
   * date 2019-10-12
   *
   * @param status 组织状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<OrganizationEntity> listByStatus(Integer status);

}
