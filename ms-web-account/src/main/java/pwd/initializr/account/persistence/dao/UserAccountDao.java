package pwd.initializr.account.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pwd.initializr.account.persistence.entity.UserAccountEntity;

/**
 * (UserAccountEntity)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-18 22:35:17
 */
public interface UserAccountDao {

  /**
   * <h2>通过实体作为筛选条件统计</h2>
   * date 2020-07-27 22:07
   *
   * @param userAccountEntity 实例对象
   * @return java.lang.Long
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Long countAllByCondition(@Param("userAccountEntity") UserAccountEntity userAccountEntity);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Long id);

  /**
   * 通过主键删除数据
   *
   * @param ids 主键
   * @return 影响行数
   */
  int deleteByIds(List<Long> ids);

  /**
   * 新增数据
   *
   * @param userAccount 实例对象
   * @return 影响行数
   */
  int insert(UserAccountEntity userAccount);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param userAccountEntity 实例对象
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<UserAccountEntity> queryAllByCondition(
      @Param("userAccountEntity") UserAccountEntity userAccountEntity, @Param("offset") Long offset,
      @Param("limit") Long limit);

  /**
   * 通过实体作为筛选条件查询
   *
   * @param uid 用户ID
   * @return 对象列表
   */
  List<UserAccountEntity> queryAllByUid(@Param("uid") Long uid);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  UserAccountEntity queryById(Long id);

  /**
   * 修改数据
   *
   * @param userAccount 实例对象
   * @return 影响行数
   */
  int update(UserAccountEntity userAccount);

}